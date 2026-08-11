package com.InsightIQ.InsightIQ.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.jdbc.core.JdbcTemplate;

@Service
public class AIQueryServiceImpl implements AIQueryService {


    private final JdbcTemplate jdbcTemplate;
    private final ChatClient chatClient;

    public AIQueryServiceImpl(JdbcTemplate jdbcTemplate, ChatClient.Builder builder) {
        this.jdbcTemplate = jdbcTemplate;
        this.chatClient = builder.build();
    }
    //
    @Override
    public String process(String question) {

        String sql = generateSQL(question);

        if(sql.equalsIgnoreCase("INVALID")){
            return "INVALID - Only table related question allowed";
        }
        if(!isSafe(sql)){
            return "INVALID - Only table related question allowed";
        }

        try {
            System.out.println( "Generate Query "+sql);
            List<Map<String,Object>> result =  jdbcTemplate.queryForList(sql);

            if(result.isEmpty()){

                return "INVALID - No question found";
            }

            System.out.println(result);

            // convert into human language

            return toNaturalLanguage(question,result);

        }
        catch (Exception e){
            e.printStackTrace();
            return "Query failed: - "+e.getMessage();
        }

    }

    // Read ble language convert : -
    private String toNaturalLanguage(String question, List<Map<String,Object>> result){
        String prompt = """
                Convert database result into a human readable answer.
                
                User Questions:
                
                """ + question + """
                
                DB Results:
                """ + result.toString() + """
                
                Rules:
                - Answer clearly ( Don't write too much )
                - Do not show JSON
                - DO not explain SQL queries;
                """;
        return Objects.requireNonNull(chatClient
                        .prompt()
                        .user(prompt)
                        .call()
                        .content())
                        .trim();
    }




    // validation layer
    private boolean isSafe(String sql) {
        String lowerCase = sql.toLowerCase().trim();

        return lowerCase.startsWith("select")
                && !lowerCase.contains("drop")
                && !lowerCase.contains("delete")
                && !lowerCase.contains("update")
                && !lowerCase.contains("insert")
                && !lowerCase.contains("alter")
                && !lowerCase.contains("truncate")
                && !lowerCase.contains("create");
    }


    //Sql query
    private String generateSQL(String question) {
        String prompt =   """
            You are a MySQL SQL generator.

            Table: Product_sales

            Columns:
            id, car_brand, car_color, car_model, car_number,
            city, customer_email, customer_name, contact_number,
            engine, fuel_type, mileage, payment_mode, price,
            state, time_of_purchase, date_of_purchase,
            warranty_period, year

            Rules:
            - Generate only SELECT queries.
            - Use only Product_sales and the above columns.
            - Never generate INSERT, UPDATE, DELETE, DROP, ALTER or TRUNCATE.
            - If the question is unrelated to car sales data, return INVALID.
            - If the question asks about current stock/available cars,
              return INVALID because this table contains sold cars only.
            - Return only SQL. No explanation. No markdown.

            Question:
            """ + question;

        return  chatClient.prompt().user(prompt).call().content().trim();
    }
}
