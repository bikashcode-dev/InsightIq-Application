package com.InsightIQ.InsightIQ.Controller;

import com.InsightIQ.InsightIQ.commons.response.ApiResponse;
import com.InsightIQ.InsightIQ.dto.UploadSalesResponse;
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
public class CarSalesController {

    @PostMapping("/upload-csv")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) {

        //Hear we chack file is available or not
        if (file.isEmpty()) {

            // Response Status
            UploadSalesResponse response = new UploadSalesResponse(0, 0, 0);

            ApiResponse<UploadSalesResponse> apiResponse = new ApiResponse<>(
                    false,
                    "The File is Empty",
                    response,
                    BAD_REQUEST.value());

            return new ResponseEntity<>(
                    apiResponse,
                    HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    // method create api response  chack
    public static ApiResponse<UploadSalesResponse> getApiResponse(UploadSalesResponse response) {

        String message ;
        boolean success;

        if(response.getFailedCount()==0){
            message ="Uploaded All Records Successfully";
            success = true;
        }
        else if (response.getSuccessCount()==0){
            message = "All records fail to upload";
            success = false;
        }
        else{
            message = "Uploaded Successfully with errors" + response.getFailedCount() + "rows failed";
            success = false;
        }
        return new ApiResponse<UploadSalesResponse>(success,message,response,HttpStatus.OK.value());
    }

}
