package com.InsightIQ.InsightIQ.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.jdbc.core.JdbcTemplate;

@Service
public class AIQueryServiceImpl implements AIQueryService {

    private static final Logger log = LoggerFactory.getLogger(AIQueryServiceImpl.class);

    private final JdbcTemplate jdbcTemplate;
    private final ChatClient chatClient;

    public AIQueryServiceImpl(
            JdbcTemplate jdbcTemplate,
            ChatClient.Builder builder)
    {
        this.jdbcTemplate = jdbcTemplate;
        this.chatClient = builder.build();
    }
    //
    @Override
    public String process(String question) {

        String sql = generateSQL(question);

        if(sql.equalsIgnoreCase("AI_ERROR")){
        return "AI_ERROR - IS temporarily unavailable";
        }
        if(!isSafe(sql)){
            return "INVALID - Only table related question allowed";
        }

        try {
            log.debug("Generate SQL query :{}",sql);
            List<Map<String,Object>> result =  jdbcTemplate.queryForList(sql);

            if(result.isEmpty()){

                return "INVALID - No question found";
            }

            log.debug("Query returned: {}", result.size());

            // convert into human language

            return toNaturalLanguage(question,result);

        }
        catch (Exception e){
            log.error(" Data query execution failed" , e);
            return "Query failed: - "+e.getMessage();
        }

    }

    // Read ble language convert : -
    private String toNaturalLanguage(
            String question,
            List<Map<String,Object>> result){

        try {
            log.debug("Converting database result into natural language");
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
        catch (Exception e){
            log.error("AI - response generation failed " , e);
            return "Unable to convert database result into natural language";
        }
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
        try {
            log.debug("Sending request to ai for generation");

            String sql = chatClient
                    .prompt(prompt)
                    .call().
                    content();

            if (sql == null || sql.isBlank()){
                log.warn("AI  return empty string");
                return "INVALID";
            }

            log.debug("Sending request to AI query");
            return sql.trim();

        } catch (Exception e){
            log.error("AI Sql generation failed" , e);
            return "Unable to generate SQL query";
        }
    }
}
