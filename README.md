<div align="center">

<img src="https://capsule-render.vercel.app/api?type=waving&color=0:0f2027,50:2c5364,100:00d9ff&height=220&section=header&text=InsightIQ&fontSize=70&fontColor=ffffff&animation=fadeIn&fontAlignY=38&desc=AI-Powered%20Sales%20Intelligence%20%26%20Business%20Analytics%20Platform&descAlignY=58&descSize=18" width="100%"/>

<img src="https://readme-typing-svg.demolab.com?font=Fira+Code&weight=600&size=24&duration=3000&pause=800&color=00D9FF&center=true&vCenter=true&width=780&lines=Upload+a+CSV.+Ask+in+Plain+English.+Get+AI+Insights.;Natural+Language+%E2%86%92+SQL+%E2%86%92+Insights+%E2%86%92+Dashboards;Spring+Boot+%2B+Spring+AI+%2B+Ollama+%2B+MySQL+%2B+Python" alt="Typing SVG" />

<br/>

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring AI](https://img.shields.io/badge/Spring%20AI-Enabled-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-Database-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Python](https://img.shields.io/badge/Python-Visualization-3776AB?style=for-the-badge&logo=python&logoColor=white)
![Ollama](https://img.shields.io/badge/Ollama-Local%20LLM-000000?style=for-the-badge&logo=ollama&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-brightgreen?style=for-the-badge)

<br/>

[![Stars](https://img.shields.io/github/stars/bikashcode-dev/InsightIq-Application-Backend?style=for-the-badge&color=yellow)](https://github.com/bikashcode-dev/InsightIq-Application-Backend/stargazers)
[![Forks](https://img.shields.io/github/forks/bikashcode-dev/InsightIq-Application-Backend?style=for-the-badge&color=blue)](https://github.com/bikashcode-dev/InsightIq-Application-Backend/forks)
[![Last Commit](https://img.shields.io/github/last-commit/bikashcode-dev/InsightIq-Application-Backend?style=for-the-badge&color=orange)](https://github.com/bikashcode-dev/InsightIq-Application-Backend/commits)

</div>

---

## 📌 Table of Contents

- [Overview](#-overview)
- [Problem Statement](#-problem-statement)
- [Live Demo Preview](#-live-demo-preview)
- [Key Features](#-key-features)
- [System Architecture](#-system-architecture)
- [How It Works — Request Flow](#-how-it-works--request-flow)
- [Natural Language → SQL Engine](#-natural-language--sql-engine)
- [SQL Safety Layer](#-sql-safety-layer)
- [AI Sales Intelligence](#-ai-sales-intelligence)
- [Tech Stack](#️-tech-stack)
- [Example Use Case](#-example-use-case)
- [Security](#-security)
- [Roadmap](#-roadmap)
- [Target Users](#-target-users)
- [Why InsightIQ?](#-why-insightiq)
- [Connect With Me](#-connect-with-me)

---

## 📖 Overview

**InsightIQ** is an AI-powered Business Intelligence and Sales Analytics platform that turns massive, messy sales spreadsheets into clear, actionable insights — automatically.

Instead of manually digging through thousands (or millions) of rows, a user simply **uploads a CSV**, and InsightIQ takes over: validating the data, analyzing it, generating business insights, building interactive dashboards, and answering plain-English questions using AI.

> Built with **Spring Boot**, **Spring AI**, **Ollama**, **MySQL**, and **Python** — a complete end-to-end AI analytics pipeline.

---

## 🎯 Problem Statement

Businesses sit on years of sales data, but turning that data into decisions usually needs SQL expertise and manual effort. Managers keep asking the same kinds of questions:

| ❓ Common Business Question |
|---|
| Which product sells the most? |
| Which products generate the highest profit? |
| Which products are underperforming? |
| Which categories are trending? |
| What caused revenue to decrease? |
| Which inventory should be restocked? |
| Which products should be discontinued? |

Traditional dashboards only draw charts — **they don't explain the business.**

> 💡 **InsightIQ acts like an experienced Sales Manager and Business Analyst** — helping decision-makers *understand* their data instead of just staring at it.

---

## 🎥 Live Demo Preview

<div align="center">

<!--
  📸 RECRUITER TIP: Replace the line below with a real screen-recording GIF or screenshot.
  Record your dashboard with ScreenToGif / Peek, save as demo.gif inside a `docs/` or `assets/` folder,
  then point the path below at it — this is the single biggest upgrade you can make to this README.
-->
<img src="docs/assets/demo.gif" alt="InsightIQ Demo" width="85%" />

<sub>⬆️ Replace this with a GIF of: CSV upload → AI chat question → generated SQL → dashboard appearing</sub>

</div>

---

## ✨ Key Features

<table>
<tr>
<td width="50%" valign="top">

### 📂 Massive CSV Data Processing
- Upload datasets with thousands → millions of records
- Fast ingestion, validation & parsing
- Works across multiple business domains

</td>
<td width="50%" valign="top">

### 🤖 AI Business Chat Assistant
- Ask questions in **plain English**
- No SQL knowledge required
- AI explains results in business language

</td>
</tr>
<tr>
<td width="50%" valign="top">

### 🧠 Natural Language → SQL Engine
- Converts questions into optimized SQL
- Powered by **Spring AI + Ollama**
- Built-in safety validation layer

</td>
<td width="50%" valign="top">

### 📈 Interactive Analytics Dashboard
- Auto-generated charts via Python
- Trends, rankings, comparisons, heatmaps
- Bar / Line / Pie visualizations

</td>
</tr>
</table>

**Example questions you can ask InsightIQ:**

```
💬 "Which product sold the most this month?"
💬 "Show the top 10 products by revenue."
💬 "Which products are causing losses?"
💬 "Compare this month's sales with last month."
💬 "Which category should receive more investment?"
```

---

## 🏗 System Architecture

```mermaid
flowchart LR
    subgraph Client["🖥️ Client Layer"]
        UI[User / Recruiter Demo UI]
    end

    subgraph Backend["☕ Spring Boot Backend"]
        API[REST API Layer]
        AI[Spring AI + Ollama<br/>NL → SQL Engine]
        SAFE[SQL Safety Validator]
        SEC[Spring Security]
    end

    subgraph Data["🗄️ Data Layer"]
        DB[(MySQL Database)]
        CSV[[CSV Upload]]
    end

    subgraph Viz["🐍 Python Visualization Engine"]
        PY[Pandas / NumPy / Matplotlib<br/>Plotly / Seaborn]
    end

    UI -->|Upload CSV| API
    UI -->|Ask Question| API
    CSV --> API
    API --> SEC
    SEC --> AI
    AI --> SAFE
    SAFE -->|Validated SELECT only| DB
    DB --> API
    API --> PY
    PY -->|Interactive Charts| UI

    style Client fill:#0f2027,stroke:#00d9ff,color:#fff
    style Backend fill:#1b2735,stroke:#6DB33F,color:#fff
    style Data fill:#1b2735,stroke:#4479A1,color:#fff
    style Viz fill:#1b2735,stroke:#FFD43B,color:#fff
```

---

## 🔄 How It Works — Request Flow

```mermaid
sequenceDiagram
    actor User
    participant UI as Frontend
    participant API as Spring Boot API
    participant AI as Spring AI + Ollama
    participant DB as MySQL
    participant PY as Python Engine

    User->>UI: Upload CSV file
    UI->>API: POST /upload
    API->>DB: Validate & store data
    DB-->>API: ✅ Stored successfully

    User->>UI: Ask "Top 10 products by revenue?"
    UI->>API: POST /ask
    API->>AI: Send natural language question
    AI-->>API: Generated SQL query
    API->>API: 🛡️ SQL Safety Check (SELECT only)
    API->>DB: Execute validated query
    DB-->>API: Result set
    API->>AI: Analyze results
    AI-->>API: Business insight summary
    API->>PY: Send data for visualization
    PY-->>API: Interactive chart
    API-->>UI: Insight + Chart + Explanation
    UI-->>User: 📊 Dashboard + 💬 AI Answer
```

---

## 🧠 Natural Language → SQL Engine

```mermaid
flowchart TD
    Q[💬 User Question] --> A[Spring AI + Ollama]
    A --> B[Generate SQL Query]
    B --> C{🛡️ SQL Validation Layer}
    C -->|❌ DELETE / DROP / UPDATE / ALTER / TRUNCATE / INSERT| X[🚫 Blocked]
    C -->|✅ SELECT only| D[Execute Query]
    D --> E[Analyze Results]
    E --> F[Generate Business Insight]
    F --> G[Python Visualization]
    G --> H[📊 Interactive Dashboard]

    style X fill:#ff4d4d,color:#fff
    style H fill:#00d9ff,color:#000
```

---

## 🛡 SQL Safety Layer

Only **read-only** operations are ever allowed to touch the database.

| ✅ Allowed | 🚫 Blocked |
|---|---|
| `SELECT` | `DELETE` |
| | `DROP` |
| | `UPDATE` |
| | `ALTER` |
| | `TRUNCATE` |
| | `INSERT` |

---

## 📊 AI Sales Intelligence

InsightIQ automatically surfaces:

`Highest Selling Products` · `Lowest Selling Products` · `Trending Products` · `Declining Products` · `Highest Revenue Products` · `Highest Profit Products` · `Loss-Making Products` · `Best/Worst Performing Categories` · `Monthly & Yearly Growth` · `Revenue & Profit Trends` · `Inventory Performance` · `Regional Sales Performance` · `Customer Purchase Trends` · `Business Opportunities` · `Actionable Recommendations`

**Visualization types generated:** Sales Trends · Revenue Charts · Profit/Loss Analysis · Monthly & Yearly Comparisons · Product Ranking · Category Performance · Revenue Distribution · Region-wise Sales · Heatmaps · Pie / Line / Bar Charts

---

## ⚙️ Tech Stack

<div align="center">

**Backend**

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat-square&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=flat-square&logo=springboot&logoColor=white)
![Spring AI](https://img.shields.io/badge/Spring_AI-6DB33F?style=flat-square&logo=spring&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=flat-square&logo=springsecurity&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=flat-square&logo=hibernate&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=flat-square&logo=apachemaven&logoColor=white)

**Database & AI**

![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white)
![Ollama](https://img.shields.io/badge/Ollama-000000?style=flat-square&logo=ollama&logoColor=white)

**Data Visualization**

![Python](https://img.shields.io/badge/Python-3776AB?style=flat-square&logo=python&logoColor=white)
![Pandas](https://img.shields.io/badge/Pandas-150458?style=flat-square&logo=pandas&logoColor=white)
![NumPy](https://img.shields.io/badge/NumPy-013243?style=flat-square&logo=numpy&logoColor=white)
![Matplotlib](https://img.shields.io/badge/Matplotlib-11557c?style=flat-square)
![Plotly](https://img.shields.io/badge/Plotly-3F4F75?style=flat-square&logo=plotly&logoColor=white)
![Seaborn](https://img.shields.io/badge/Seaborn-4c72b0?style=flat-square)

</div>

---

## 🚗 Example Use Case

> Imagine a **car dealership** with over **2 million sales records**.

1. Upload the CSV file
2. Ask InsightIQ:
   - *"Which car model generated the highest revenue?"*
   - *"Which SUV is selling the fastest?"*
   - *"Which sedan has declining demand?"*
   - *"Which brand generates the highest profit?"*
   - *"Which inventory should be restocked?"*
3. Get AI-generated recommendations **with interactive charts** — instantly.

**Supported domains:** Car Showrooms · Shopping Malls · Retail Stores · Electronics · Fashion · Grocery · Medical Stores · Restaurants · Manufacturing · FMCG · E-commerce · Wholesale · Any product-based business

---

## 🔐 Security

- ✅ Spring Security Authentication
- ✅ Secure REST APIs
- ✅ Role-Based Access Control
- ✅ SQL Validation Layer (SELECT-only execution)
- ✅ Protected Dashboard Access

---

## 🚀 Roadmap

- [ ] Excel File Support
- [ ] JSON Import
- [ ] PDF Report Generation
- [ ] Excel Export
- [ ] AI Report Generator
- [ ] Predictive Sales Forecasting
- [ ] Customer Segmentation
- [ ] Inventory Forecasting
- [ ] Scheduled Email Reports
- [ ] Voice-Based AI Queries
- [ ] Multi-Tenant Architecture
- [ ] Docker Deployment
- [ ] Kubernetes Support
- [ ] Cloud Deployment (AWS / Azure / GCP)
- [ ] Real-Time Streaming Analytics

---

## 💼 Target Users

`Sales Managers` · `Business Owners` · `Retail Companies` · `Store Managers` · `Data Analysts` · `Business Analysts` · `Decision Makers` · `Operations Teams`

---

## 🌟 Why InsightIQ?

| Traditional Dashboards | 🚀 InsightIQ |
|---|---|
| Shows charts only | Shows charts **+ explains what they mean** |
| Requires SQL knowledge | Ask questions in **plain English** |
| Static reports | **AI-generated**, dynamic insights |
| Manual analysis | **Automated** Sales Manager-style analysis |
| Generic metrics | Actionable, business-specific recommendations |

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
