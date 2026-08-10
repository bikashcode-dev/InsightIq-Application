package com.InsightIQ.InsightIQ.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AIQueryServiceImpl implements AIQueryService {


    private ChatClient chatClient;

    public AIQueryServiceImpl(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }


    //
    @Override
    public String process(String question) {
        return generateSQL(question);
    }


    //Sql query
    private String generateSQL(String question) {
        String prompt = """
                        You are an expert MySQL SQL query generator for the InsightIQ
                        Car Sales Analytics application.
                
                        Your ONLY job is to convert the user's natural-language question
                        into a valid MySQL SELECT query using ONLY the database schema
                        provided below.
                
                        =========================
                        DATABASE INFORMATION
                        =========================
                
                        Database: AutoSalesDealerAdvisor
                        Table: Product_sales
                
                        Available columns:
                
                        - id                  BIGINT
                        - car_brand           VARCHAR
                        - car_color           VARCHAR
                        - car_model           VARCHAR
                        - car_number          VARCHAR
                        - city                VARCHAR
                        - customer_email      VARCHAR
                        - customer_name       VARCHAR
                        - contact_number      VARCHAR
                        - engine              INT
                        - fuel_type           VARCHAR
                        - mileage             DOUBLE
                        - payment_mode        VARCHAR
                        - price               BIGINT
                        - state               VARCHAR
                        - time_of_purchase    TIME
                        - date_of_purchase    DATE
                        - warranty_period     VARCHAR
                        - year                INT
                
                        =========================
                        DATA CONTEXT
                        =========================
                
                        IMPORTANT:
                        This table contains SOLD vehicle records.
                
                        Therefore:
                        - COUNT(*) represents the number of recorded vehicle sales.
                        - The table does NOT contain current inventory/stock information.
                        - Never calculate or claim remaining stock.
                        - Never assume that total inventory = sold vehicles.
                        - If the user asks about current stock, available inventory,
                          unsold vehicles, or remaining vehicles, return:
                
                          INVALID
                
                        =========================
                        SQL RULES
                        =========================
                
                        1. Generate ONLY SELECT queries.
                
                        2. Never generate:
                           INSERT
                           UPDATE
                           DELETE
                           DROP
                           ALTER
                           TRUNCATE
                           CREATE
                           REPLACE
                           GRANT
                           or any other data-modifying statement.
                
                        3. Use ONLY the Product_sales table.
                
                        4. Use ONLY the columns listed above.
                
                        5. Never invent a column name.
                
                        6. Never invent a table name.
                
                        7. Do not use SELECT * unless the user explicitly asks
                           for complete records.
                
                        8. For "how many cars were sold", use:
                           COUNT(*)
                
                        9. For total sales value/revenue, use:
                           SUM(price)
                
                        10. For average vehicle price, use:
                            AVG(price)
                
                        11. For highest vehicle price, use:
                            MAX(price)
                
                        12. For lowest vehicle price, use:
                            MIN(price)
                
                        13. For brand-wise sales, use:
                            GROUP BY car_brand
                
                        14. For model-wise sales, use:
                            GROUP BY car_model
                
                        15. For state-wise sales, use:
                            GROUP BY state
                
                        16. For city-wise sales, use:
                            GROUP BY city
                
                        17. For fuel-type sales, use:
                            GROUP BY fuel_type
                
                        18. For payment-mode analysis, use:
                            GROUP BY payment_mode
                
                        19. For yearly sales, use:
                            GROUP BY year
                
                        20. For monthly sales, use:
                            MONTH(date_of_purchase)
                
                        21. For date-based filtering, use date_of_purchase.
                
                        22. For year-based filtering, use year or YEAR(date_of_purchase)
                            where appropriate.
                
                        23. When the user asks for the "top", "highest", "most",
                            or "best", use ORDER BY ... DESC with LIMIT 1 when
                            appropriate.
                
                        24. When the user asks for the "lowest", "least", or "worst",
                            use ORDER BY ... ASC with LIMIT 1 when appropriate.
                
                        25. If the question cannot be answered using the available
                            columns and data, return exactly:
                
                            INVALID
                
                        26. If the question asks for information that requires data
                            not present in the table, return:
                
                            INVALID
                
                        27. Do not explain the SQL.
                
                        28. Do not return Markdown.
                
                        29. Do not return ```sql blocks.
                
                        30. Return ONLY the SQL query or exactly INVALID.
                
                        =========================
                        EXAMPLES
                        =========================
                
                        User: How many cars were sold?
                
                        Output:
                        SELECT COUNT(*) FROM Product_sales;
                
                        User: Which brand sold the most cars?
                
                        Output:
                        SELECT car_brand, COUNT(*) AS sales_count
                        FROM Product_sales
                        GROUP BY car_brand
                        ORDER BY sales_count DESC
                        LIMIT 1;
                
                        User: Which state had the highest sales?
                
                        Output:
                        SELECT state, COUNT(*) AS sales_count
                        FROM Product_sales
                        GROUP BY state
                        ORDER BY sales_count DESC
                        LIMIT 1;
                
                        User: What is the average car price?
                
                        Output:
                        SELECT AVG(price) AS average_price
                        FROM Product_sales;
                
                        User: What was the total sales value?
                
                        Output:
                        SELECT SUM(price) AS total_revenue
                        FROM Product_sales;
                
                        User: How many cars were sold in 2025?
                
                        Output:
                        SELECT COUNT(*)
                        FROM Product_sales
                        WHERE year = 2025;
                
                        User: Which month had the highest sales in 2025?
                
                        Output:
                        SELECT MONTH(date_of_purchase) AS month,
                               COUNT(*) AS sales_count
                        FROM Product_sales
                        WHERE year = 2025
                        GROUP BY MONTH(date_of_purchase)
                        ORDER BY sales_count DESC
                        LIMIT 1;
                
                        User: How many cars are currently in stock?
                
                        Output:
                        INVALID
                
                        User question:
                        """ + question;

        return  chatClient.prompt().user(prompt).call().content().trim();
    }
}
