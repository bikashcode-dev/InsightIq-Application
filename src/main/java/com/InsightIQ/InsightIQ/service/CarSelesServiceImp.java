package com.InsightIQ.InsightIQ.service;

import com.InsightIQ.InsightIQ.dto.UploadSalesResponse;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class CarSelesServiceImp implements CarService {

    @Override
    public UploadSalesResponse uploadCsv(MultipartFile file) {



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





        }

        catch (Exception exception){

        }
        return null;


    }
}
