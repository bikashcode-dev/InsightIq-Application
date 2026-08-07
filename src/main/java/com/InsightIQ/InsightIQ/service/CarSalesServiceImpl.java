package com.InsightIQ.InsightIQ.service;
import com.InsightIQ.InsightIQ.dto.*;
import com.InsightIQ.InsightIQ.entity.CarSaleEntity;
import com.InsightIQ.InsightIQ.repository.CarSalesRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;

@Service
public class CarSalesServiceImpl implements CarService {


    // for ActiveReports autowiring ->
    private final CarSalesRepository carSalesRepository;

    public CarSalesServiceImpl(CarSalesRepository carSalesRepository) {
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

            // -> store temporary balti me dal kr rake do all mind disturb ho gya asan tah
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
                    boolean exists = carSalesRepository.existsByCarNumber(carNumber);
                    if (exists) {
                        failCount++;
                        System.out.println("Car Number " + carNumber + " is already exit");
                        continue;
                    }
                    // set details by entity hat tari ki
                    CarSaleEntity carSales = new CarSaleEntity();

                    carSales.setCarNumber(record.get("Car Number"));
                    carSales.setCarBrand(record.get("Brand"));
                    carSales.setCarModel(record.get("Model"));
                    carSales.setCarColor(record.get("Color"));

                    carSales.setYear(Integer.parseInt(record.get("Year")));


                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    carSales.setTimeOfPurchase(LocalTime.parse(record.get("Time of Purchase")));

                    carSales.setPrice(Long.parseLong(record.get("Price (Rs)")));
                    carSales.setMileage(Double.parseDouble(record.get("Mileage (km/l)")));
                    carSales.setEngine(Integer.parseInt(record.get("Engine (cc)")));

                    carSales.setFuelType(record.get("Fuel Type"));
                    carSales.setPaymentMode(record.get("Payment Mode"));
                    carSales.setState(record.get("State"));
                    carSales.setCity(record.get("City"));
                    carSales.setCustomerName(record.get("Customer Name"));
                    carSales.setCustomerPhoneNumber(record.get("Contact Number"));
                    carSales.setCustomerEmail(record.get("Email"));
                    carSales.setWarrantyPeriod(record.get("Warranty Period (years)"));
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
            failCount++;
            throw new RuntimeException("Unable to upload(Parse) CSV file Failed" + exception.getMessage());
        }
        int  successCount = totalRecords - failCount;
        return new UploadSalesResponse(totalRecords,  successCount, failCount);
    }

    @Override
    public List<YearlyCountDto> getYearlyCarCounts() {
        return carSalesRepository.getYearlyCount();
    }


    @Override
    public List<StateCountDto> getStateWiseCarCounts() {
        return carSalesRepository.getStateWiseCount();
    }

    @Override
    public AveragePriceDto getAveragePrice() {
        return carSalesRepository.getAveragePrice();
    }

    @Override
    public List<BrandCountDto> getBrandCounts() {
        return List.of();
    }
    @Override
    public List<FuelTypeCountDto> getFuelTypeCounts() {
        return carSalesRepository.getFuelTypeCount();
    }

    @Override
    public List<PaymentModeCountDto> getPaymentModeCounts() {
        return carSalesRepository.getPaymentTypeCount();
    }

    @Override
    public List<ModelCountDto> getModelCounts() {
        return carSalesRepository.getModelCount();
    }

    @Override
    public List<MonthlySalesDto> getMonthlySalesCount() {
        return carSalesRepository.getMonthlySales();
    }

    @Override
    public List<CityCountDto> getCitesWiseCount() {
        return carSalesRepository.getCitesWiseCount();
    }

}
