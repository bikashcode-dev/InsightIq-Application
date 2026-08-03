package com.InsightIQ.InsightIQ.service;

import com.InsightIQ.InsightIQ.dto.UploadSalesResponse;
import com.InsightIQ.InsightIQ.entity.CarSaleEntity;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CarSalesRepository implements CarService {


    // for ActiveReports autowiring ->
    private final com.InsightIQ.InsightIQ.repository.CarSalesRepository carSalesRepository;

    public CarSalesRepository(com.InsightIQ.InsightIQ.repository.CarSalesRepository carSalesRepository) {
        this.carSalesRepository = carSalesRepository;
    }


    @Override
    public UploadSalesResponse uploadCsv(MultipartFile file) {


        // add list ROW data
        List<CarSaleEntity> carSaleEntities = new ArrayList<>();

        // if row fail count variable
        int failCount = 0;
        int totalRecords = 0;

        try {
            // csv(Byte) -> inputStream -> InputStreamReader -> character / text//         store it     <-  convert into byte;
            InputStream inputStream = file.getInputStream();
            // Row byte readable text convert
            InputStreamReader inputStreamReader =
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8);

            // -> store temporary balti me dal kr rake do all dimga kharab ho gya itan asan tah
            BufferedReader bufferedReader =
                    new BufferedReader(inputStreamReader);


            // CSVFormating
            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader() // Header
                    .setSkipHeaderRecord(true) // for skip -- NO treated as a data column
                    .setIgnoreHeaderCase(true) // cas - insensitive
                    .setTrim(true) //
                    .build(); // build

            CSVParser parser = CSVParser.parse(bufferedReader, csvFormat);


            for (CSVRecord record : parser.getRecords()) {
                totalRecords++;
                try {
                    // check car exit or not
                    String carNumber = record.get("Car Number");
                    boolean exit = carSalesRepository.exitsByCarNumber(carNumber);
                    if (exit) {
                        failCount++;
                        System.out.println("Car Number " + carNumber + " is already exit");
                        continue;
                    }
                    // set details by entity hat tari ki
                    CarSaleEntity carSales = new CarSaleEntity();
                    carSales.setCarNumber(record.get("Car Number"));
                    carSales.setCarNumber(record.get("Brand"));
                    carSales.setCarNumber(record.get("Model"));
                    carSales.setCarNumber(record.get("Color"));
                    carSales.setYear(Integer.parseInt(record.get("Year")));
                    carSales.setTimeOfPurchaseDate(LocalDate.parse(record.get("Date of Purchase")));
                    carSales.setTimeOfPurchaseDate(LocalDate.parse(record.get("Time of Purchase")));
                    carSales.setPrice(Long.parseLong(record.get("Price (RS)")));
                    carSales.setMileage(Double.parseDouble(record.get("Mileage (KM)")));
                    carSales.setEngine(Integer.parseInt(record.get("Engine (CC)")));
                    carSales.setFuelType(record.get("Fuel Type"));
                    carSales.setPaymentMode(record.get("Payment Mode"));
                    carSales.setState(record.get("State"));
                    carSales.setCity(record.get("City"));
                    carSales.setCountry(record.get("Country"));
                    carSales.setCustomerName(record.get("Customer Name"));
                    carSales.setCustomerPhoneNumber(record.get("Customer Phone Number"));
                    carSales.setCustomerEmail(record.get("Customer Email"));
                    carSales.setWarrantyPeriod(record.get("Warranty Period"));
                    carSaleEntities.add(carSales);

                } catch (Exception e) {
                    failCount++;
                    System.out.println("Failed to parse CSV record: " + record.getRecordNumber());
                    throw new RuntimeException(e);
                }

            }

            if (!carSaleEntities.isEmpty()) {
                carSalesRepository.saveAll(carSaleEntities);
            }

        } catch (Exception exception) {
            throw new RuntimeException("Unable to upload(Parse) CSV file Failed" + exception.getMessage());
        }
        int sucessCount = totalRecords - failCount;
        return new UploadSalesResponse(totalRecords, sucessCount, failCount);
    }
}
