package com.InsightIQ.InsightIQ.Controller;


import com.InsightIQ.InsightIQ.commons.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("/api/car-sales")
public class ProductSalesController {

    @PostMapping("/upload-csv")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) {

        //Hear we chack file is available or not
        if(file.isEmpty()){

            // Respone Status
             ApiResponse<> response = new ApiResponse<>(
                    false,
                    "The File is Empty",
                    null,
                    BAD_REQUEST.value()
                    );
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return null;

    }
}
