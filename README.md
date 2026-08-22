<div align="center">

<img src="https://capsule-render.vercel.app/api?type=waving&color=0:0f2027,50:2c5364,100:00d9ff&height=220&section=header&text=InsightIQ&fontSize=70&fontColor=ffffff&animation=fadeIn&fontAlignY=38&desc=AI-Powered%20Sales%20Intelligence%20%26%20Business%20Analytics%20Platform&descAlignY=58&descSize=18" width="100%"/>

<img src="https://readme-typing-svg.demolab.com?font=Fira+Code&weight=600&size=24&duration=3000&pause=800&color=00D9FF&center=true&vCenter=true&width=780&lines=Upload+a+CSV.+Ask+in+Plain+English.+Get+AI+Insights.;Natural+Language+%E2%86%92+SQL+%E2%86%92+Insights+%E2%86%92+Dashboards;Spring+Boot+4+%2B+Spring+AI+%2B+Ollama+%2B+MySQL" alt="Typing SVG" />

<br/>

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.0-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring AI](https://img.shields.io/badge/Spring%20AI-2.0.0-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-Database-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Ollama](https://img.shields.io/badge/Ollama-Local%20LLM-000000?style=for-the-badge&logo=ollama&logoColor=white)

<br/>

[![Stars](https://img.shields.io/github/stars/bikashcode-dev/InsightIq-Application-Backend?style=for-the-badge&color=yellow)](https://github.com/bikashcode-dev/InsightIq-Application-Backend/stargazers)
[![Forks](https://img.shields.io/github/forks/bikashcode-dev/InsightIq-Application-Backend?style=for-the-badge&color=blue)](https://github.com/bikashcode-dev/InsightIq-Application-Backend/forks)
[![Last Commit](https://img.shields.io/github/last-commit/bikashcode-dev/InsightIq-Application-Backend?style=for-the-badge&color=orange)](https://github.com/bikashcode-dev/InsightIq-Application-Backend/commits)

</div>

**InsightIQ** is a Spring Boot REST API that turns raw car-sales CSV data into aggregated analytics and answers plain-English business questions — by generating and safely executing SQL through **Spring AI + Ollama**, live against MySQL.

> ⚡ Built on the brand-new **Spring Boot 4.1.0** — not a legacy 2.x/3.x stack.

---

## 📌 Table of Contents

- [Overview](#-overview)
- [Problem Statement](#-problem-statement)
- [Key Features](#-key-features)
- [System Architecture](#-system-architecture)
- [CSV Upload Flow](#-csv-upload-flow)
- [Natural Language → SQL Engine](#-natural-language--sql-engine)
- [SQL Safety Layer](#-sql-safety-layer)
- [Data Model](#-data-model)
- [API Reference](#-api-reference)
- [Tech Stack](#️-tech-stack)
- [Getting Started](#-getting-started)
- [Companion Frontend](#-companion-frontend)
- [Honest Project Status](#-honest-project-status)
- [Roadmap](#-roadmap)
- [Connect With Me](#-connect-with-me)

---

## 📖 Overview

Instead of manually digging through spreadsheets of car-sales records, a client uploads a **CSV file**, and InsightIQ:

1. Parses and stores every row in MySQL via a validated CSV pipeline
2. Serves ready-made aggregate analytics (yearly, monthly, brand, model, state, city, fuel-type, payment-mode, average price, total sales)
3. Lets a user **ask a question in plain English** — the backend generates SQL with an LLM, validates it's read-only, executes it, and turns the result back into a natural-language answer

> Built with **Spring Boot 4**, **Spring AI (Ollama starter)**, **Spring Data JPA**, **MySQL**, and **Apache Commons CSV**.

---

## 🎯 Problem Statement

Businesses sit on years of sales data, but turning that data into decisions usually needs SQL expertise and manual effort. Managers keep asking the same kinds of questions:

| Common Business Question |
|---|
| Which product sells the most? |
| Which products generate the highest profit? |
| Which categories are trending? |
| What caused revenue to decrease? |
| Which inventory should be restocked? |

Traditional dashboards only draw charts — **they don't explain the business.**

> 💡 InsightIQ answers these questions directly, in the same words a manager would ask them.

---

## ✨ Key Features

<table>
<tr>
<td width="50%" valign="top">

### 📂 CSV Ingestion Pipeline
- `multipart/form-data` upload via `POST /api/car-sales/upload-csv`
- Row-level parsing with **Apache Commons CSV**
- Per-row success/failure counts returned, not an all-or-nothing failure
- Unique constraint on `car_number` prevents duplicate records

</td>
<td width="50%" valign="top">

### 🤖 AI Business Chat Assistant
- `POST /api/ai/ask` — ask questions in **plain English**
- No SQL knowledge required
- Answers generated in natural language, not raw JSON

</td>
</tr>
<tr>
<td width="50%" valign="top">

### 🧠 Natural Language → SQL Engine
- Powered by **Spring AI `ChatClient` + Ollama**
- Schema-constrained prompt (only `Product_sales` columns allowed)
- Out-of-scope questions return `INVALID` instead of guessing

</td>
<td width="50%" valign="top">

### 📊 11 Aggregate Analytics Endpoints
- Yearly / monthly / total sales
- Brand / model / fuel-type / payment-mode / state / city breakdowns
- Average price
- Every response wrapped in a consistent `ApiResponse<T>` envelope

</td>
</tr>
</table>

**Example questions you can ask InsightIQ:**

```
💬 "Which brand sold the most cars?"
💬 "What is the average price of cars sold in Maharashtra?"
💬 "How many CNG cars were sold in 2024?"
💬 "Which state has the highest total sales?"
```

---

## 🏗 System Architecture

```mermaid
flowchart LR
    subgraph Client["🖥️ Client"]
        UI[Frontend / API Consumer]
    end

    subgraph Backend["☕ Spring Boot 4 Backend"]
        CTRL["Controllers<br/>CarSalesController · AIController · HealthCheckController"]
        SVC["Service Layer<br/>CarSalesServiceImpl · AIQueryServiceImpl"]
        AI["Spring AI ChatClient<br/>NL → SQL"]
        SAFE["SQL Safety Validator<br/>isSafe()"]
        JPA["Spring Data JPA<br/>CarSalesRepository"]
    end

    subgraph Data["🗄️ Data Layer"]
        DB[(MySQL — Product_sales table)]
    end

    subgraph LLM["🧠 Ollama"]
        OL[Local LLM]
    end

    UI -->|POST /api/car-sales/upload-csv| CTRL
    UI -->|GET /api/car-sales/*| CTRL
    UI -->|POST /api/ai/ask| CTRL
    CTRL --> SVC
    SVC --> JPA --> DB
    SVC --> AI --> OL
    AI --> SAFE
    SAFE -->|Validated SELECT only| DB
    DB --> AI --> CTRL --> UI

    style Client fill:#0f2027,stroke:#00d9ff,color:#fff
    style Backend fill:#1b2735,stroke:#6DB33F,color:#fff
    style Data fill:#1b2735,stroke:#4479A1,color:#fff
    style LLM fill:#1b2735,stroke:#FFD43B,color:#fff
```

---

## 📤 CSV Upload Flow

```mermaid
sequenceDiagram
    actor User
    participant API as CarSalesController
    participant SVC as CarSalesServiceImpl
    participant CSV as Apache Commons CSV
    participant DB as MySQL

    User->>API: POST /api/car-sales/upload-csv (multipart file)
    API->>API: Check file.isEmpty()
    alt File is empty
        API-->>User: 400 — "The File is Empty"
    else File present
        API->>SVC: uploadCsv(file)
        SVC->>CSV: Parse rows
        loop Each row
            CSV->>DB: Insert validated CarSaleEntity
            DB-->>SVC: Success / failure per row
        end
        SVC-->>API: UploadSalesResponse(success, failed, total)
        API-->>User: 200 — "Uploaded Successfully" (with per-row counts)
    end
```

---

## 🧠 Natural Language → SQL Engine

```mermaid
flowchart TD
    Q["💬 POST /api/ai/ask<br/>raw question as request body"] --> V{Blank?}
    V -->|Yes| REJ["400 — Question cannot be empty"]
    V -->|No| GEN["generateSQL()<br/>Schema-constrained prompt via ChatClient"]
    GEN --> INV{AI returns<br/>INVALID / AI_ERROR?}
    INV -->|Yes| ERR["Return controlled error string"]
    INV -->|No| SAFE{"isSafe(sql)<br/>starts with SELECT +<br/>no DROP/DELETE/UPDATE/INSERT/ALTER/TRUNCATE/CREATE"}
    SAFE -->|Fails| BLOCK["🚫 INVALID - Only table related<br/>question allowed"]
    SAFE -->|Passes| EXEC["jdbcTemplate.queryForList(sql)"]
    EXEC --> EMPTY{Result empty?}
    EMPTY -->|Yes| NF["INVALID - No question found"]
    EMPTY -->|No| NAT["toNaturalLanguage()<br/>ChatClient converts result → plain English"]
    NAT --> OUT["✅ Human-readable answer"]

    style REJ fill:#e94560,color:#fff
    style BLOCK fill:#e94560,color:#fff
    style OUT fill:#00d9ff,color:#000
```

---

## 🛡 SQL Safety Layer

Two layers of protection before any AI-generated SQL touches the database:

**1. Prompt-level constraint** — the LLM is told the query must target only the `Product_sales` table and its known columns, and must return `INVALID` for anything unrelated (including "current stock" questions, since the table only holds *sold* cars).

**2. Code-level validation (`isSafe()`)** — regardless of what the LLM returns, the query is rejected unless it starts with `SELECT` and contains none of:

| 🚫 Blocked Keywords |
|---|
| `DROP` |
| `DELETE` |
| `UPDATE` |
| `INSERT` |
| `ALTER` |
| `TRUNCATE` |
| `CREATE` |

> Defense in depth: even a compromised or hallucinating prompt can't reach a mutating query — the check happens in Java, not just in the prompt.

---

## 🗂 Data Model

The `Product_sales` MySQL table (mapped by `CarSaleEntity`) backs every endpoint:

| Column | Type | Notes |
|---|---|---|
| `id` | `BIGINT` | Auto-generated identity |
| `car_number` | `VARCHAR` | **Unique**, not null |
| `car_brand` / `car_model` / `car_color` | `VARCHAR` | Vehicle identity |
| `year` | `INT` | Manufacture/sale year |
| `time_of_purchase` / `date_of_purchase` | `TIME` / `DATE` | Transaction timestamp |
| `price` | `BIGINT` | Sale price |
| `mileage` | `DOUBLE` | |
| `engine` | `INT` | Engine capacity |
| `fuel_type` | `VARCHAR` | Petrol / Diesel / CNG / EV etc. |
| `payment_mode` | `VARCHAR` | Cash / Credit Card / etc. |
| `state` / `city` | `VARCHAR` | Location of sale |
| `customer_name` / `contact_number` | `VARCHAR` | Buyer info |
| `warranty_period` | — | Warranty terms |

> This is exactly the schema the AI's SQL-generation prompt is constrained to — nothing more, nothing less.

---

## 📡 API Reference

Base path: **`/api`**

<details open>
<summary><b>🩺 Health</b></summary>

| Method | Endpoint | Response |
|---|---|---|
| `GET` | `/Health/ok` | Plain text health status |
| `GET` | `/Health/api/check` | Plain text health status |

</details>

<details open>
<summary><b>📂 Car Sales — Ingestion</b></summary>

| Method | Endpoint | Body | Response |
|---|---|---|---|
| `POST` | `/api/car-sales/upload-csv` | `multipart/form-data` — field `file` | `{ successCount, failedCount, totalCount }` |

</details>

<details open>
<summary><b>📊 Car Sales — Analytics</b></summary>

| Method | Endpoint | Params | Returns |
|---|---|---|---|
| `GET` | `/api/car-sales/yearly-count` | — | Sales grouped by year |
| `GET` | `/api/car-sales/monthly-sales` | `?year=` | Sales grouped by month for a given year |
| `GET` | `/api/car-sales/state-count` | — | Sales grouped by state |
| `GET` | `/api/car-sales/city-count` | — | Sales grouped by city |
| `GET` | `/api/car-sales/brand-count` | — | Sales grouped by brand |
| `GET` | `/api/car-sales/model-count` | — | Sales grouped by model |
| `GET` | `/api/car-sales/fuel-type-count` | — | Sales grouped by fuel type |
| `GET` | `/api/car-sales/payment-mode-count` | — | Sales grouped by payment mode |
| `GET` | `/api/car-sales/average-price` | — | Average sale price |
| `GET` | `/api/car-sales/total-sales` | — | Total sales count |

</details>

<details open>
<summary><b>🤖 AI</b></summary>

| Method | Endpoint | Body | Returns |
|---|---|---|---|
| `POST` | `/api/ai/ask` | Raw text question | Natural-language answer |

</details>

Every analytics response is wrapped in a consistent envelope:

```json
{
  "success": true,
  "message": "Data Read Successfully",
  "data": [ /* ... */ ],
  "statusCode": 200
}
```

---

## ⚙️ Tech Stack

<div align="center">

**Backend**

![Java](https://img.shields.io/badge/Java_17-ED8B00?style=flat-square&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot_4.1.0-6DB33F?style=flat-square&logo=springboot&logoColor=white)
![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-6DB33F?style=flat-square&logo=spring&logoColor=white)
![Spring Web MVC](https://img.shields.io/badge/Spring_Web_MVC-6DB33F?style=flat-square&logo=spring&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-BC0031?style=flat-square)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=flat-square&logo=apachemaven&logoColor=white)

**Database & AI**

![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white)
![Spring AI](https://img.shields.io/badge/Spring_AI_2.0.0-6DB33F?style=flat-square&logo=spring&logoColor=white)
![Ollama](https://img.shields.io/badge/Ollama-000000?style=flat-square&logo=ollama&logoColor=white)

**Data Ingestion**

![Apache Commons CSV](https://img.shields.io/badge/Apache_Commons_CSV-D22128?style=flat-square&logo=apache&logoColor=white)

</div>

---

## 🚀 Getting Started

```bash
# 1. Clone the repo
git clone https://github.com/bikashcode-dev/InsightIq-Application-Backend.git
cd InsightIq-Application-Backend

# 2. Configure local settings
# Create src/main/resources/application.yaml (gitignored — not committed)
# with your MySQL connection and Ollama base URL/model

# 3. Run
./mvnw spring-boot:run
```

**Prerequisites:**
- Java 17
- MySQL running with an accessible schema
- [Ollama](https://ollama.com) running locally with a chat-capable model pulled

> `application.yaml` is intentionally excluded via `.gitignore` — configure your own local DB credentials and Ollama settings; nothing sensitive is committed to the repo.

---

## 🧪 Testing

```bash
./mvnw test
```

| Metric | Current |
|---|---|
| Test files | 1 (`InsightIqApplicationTests`) |
| Coverage | Application-context smoke test only |
| CI/CD pipeline | Not yet configured |

> Being upfront here on purpose: this is the honest current state, not an inflated claim. Expanding coverage for `CarSalesServiceImpl` (CSV parsing) and `AIQueryServiceImpl` (`isSafe()` in particular, since it's the security boundary) is the single highest-value next step — tracked in the Roadmap.

---

## 🔗 Companion Frontend

This backend is consumed by a separate **Streamlit analytics frontend**:
[InsightIq-Application-Python](https://github.com/bikashcode-dev/InsightIq-Application-Python) — turns these REST responses into interactive dashboards, KPIs, and an AI chat UI.

```mermaid
flowchart LR
    PY["🐍 InsightIq-Application-Python<br/>Streamlit Dashboard"] -->|REST calls| BE["☕ InsightIq-Application-Backend<br/>(this repo)"]
    BE --> DB[(MySQL)]
    BE --> OL[Ollama]

    style PY fill:#1a1a2e,stroke:#F63366,color:#fff
    style BE fill:#0f2027,stroke:#00d9ff,color:#fff
```

---

## 🔍 Honest Project Status

| Claimed Elsewhere | Actual Code Status |
|---|---|
| Spring Boot 3.x | ✅ Actually **Spring Boot 4.1.0** — newer than claimed |
| Java 21 | ⚠️ `pom.xml` sets `java.version` to **17** |
| Spring Security Authentication | ⚠️ Not yet in `pom.xml` — API is fully open today, tracked in Roadmap |
| MIT License | ⚠️ No `LICENSE` file currently in the repo — add one to make reuse terms explicit |
| Data visualization | ✅ Handled by the separate Python frontend, not this backend |
| Role-Based Access Control | ⚠️ Not yet implemented |
| Automated test suite | ⚠️ Only a single Spring context smoke test exists today |

> This section exists on purpose — a README that only lists aspirations isn't useful to a recruiter reading the actual code. What's built is built; what isn't is on the roadmap below.

---

## 🚀 Roadmap

**Housekeeping (quick wins)**
- [ ] Add a `LICENSE` file (repo currently has none)
- [ ] Add a GitHub Actions CI workflow (`mvn test` on every push)
- [ ] Expand unit tests for `CarSalesServiceImpl` and `AIQueryServiceImpl.isSafe()`

**Product features**
- [ ] Spring Security + JWT-protected endpoints
- [ ] Role-Based Access Control
- [ ] Excel (.xlsx) upload support
- [ ] PDF / Excel report export
- [ ] Predictive sales forecasting
- [ ] Docker Compose (backend + MySQL + Ollama in one command)
- [ ] Cloud deployment guide (AWS / Azure / GCP)
- [ ] Pagination for large aggregate responses

---

<div align="center">

## 🤝 Connect With Me

**Bikash Sah**
Java Full-Stack Developer · Spring Boot · Spring AI · Business Intelligence · Data Analytics

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Connect-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/bikash-sah-java)
[![Email](https://img.shields.io/badge/Email-Say%20Hello-D14836?style=for-the-badge&logo=gmail&logoColor=white)](mailto:bikashcod@gmail.com)
[![GitHub](https://img.shields.io/badge/GitHub-Follow-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/bikashcode-dev)

<img src="https://capsule-render.vercel.app/api?type=waving&color=0:00d9ff,100:0f2027&height=120&section=footer" width="100%"/>

</div>
