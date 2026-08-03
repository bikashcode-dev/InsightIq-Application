package com.InsightIQ.InsightIQ.service;

import com.InsightIQ.InsightIQ.dto.UploadSalesResponse;
import com.InsightIQ.InsightIQ.entity.CarSaleEntity;
import com.InsightIQ.InsightIQ.repository.CarSalesRepository;
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

public class CarSelesServiceImp implements CarService {


    // for carslaerepoisty autowaring ->
    private CarSalesRepository carSalesRepository;

    public CarSelesServiceImp(CarSalesRepository carSalesRepository) {
        this.carSalesRepository = carSalesRepository;
    }



    @Override
    public UploadSalesResponse uploadCsv(MultipartFile file) {


        // add list ROW data
        List<CarSaleEntity> carSaleEntities = new ArrayList<CarSaleEntity>();

        // if row fail count varible
        int failCount = 0;

        try {
            // csv(Byte) -> inputStream -> InputStreamReader -> charactor / text//         store it     <-  convert into byte;
            InputStream inputStream = file.getInputStream();
            // Row byte readble text convert
            InputStreamReader inputStreamReader =
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8);

             // -> store temporary balti me dal kr rakh do alal dimga kharab ho gya itan asan tah
            BufferedReader bufferedReader =
                    new BufferedReader(inputStreamReader);


            // CSVFormating
            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader() // Header
                    .setSkipHeaderRecord(true) // for skip -- NO treadted as a data coume
                    .setIgnoreHeaderCase(true) // cas - insensitive
                    .setTrim(true) //
                    .build(); // build

            CSVParser parser = CSVParser.parse(bufferedReader,csvFormat);


            for(CSVRecord record : parser.getRecords()){
                // cheak car exit or not
               String carNumber = record.get("Car Number");
               boolean exit = carSalesRepository.exitsByCarNumber(carNumber);
               if(exit){
                   System.out.println("Car Number "+carNumber+" is already exit");
                   failCount++;
                   continue;
               }

               // set deatisl by entitty hat tari ki
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
            }
        }

        catch (Exception exception){

        }
        return null;
    }
}
