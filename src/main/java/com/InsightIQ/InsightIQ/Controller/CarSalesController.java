package com.InsightIQ.InsightIQ.Controller;
import com.InsightIQ.InsightIQ.commons.response.ApiResponse;
import com.InsightIQ.InsightIQ.dto.*;
import com.InsightIQ.InsightIQ.service.CarSalesServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;


@RestController
@RequestMapping("/api/car-sales")
public class CarSalesController {

    private final CarSalesServiceImpl carSalesService;

    public CarSalesController(CarSalesServiceImpl carSalesService) {
        this.carSalesService = carSalesService;
    }

    @PostMapping("/upload-csv")
    public ResponseEntity<?> uploadCsv(@RequestParam("file") MultipartFile file) {


        //Hear we chack file is available or not
        if (file.isEmpty()) {

            UploadSalesResponse response = new UploadSalesResponse(0, 0, 0);
            ApiResponse<UploadSalesResponse> apiResponse = new ApiResponse<>(
                    false,
                    "The File is Empty",
                    response,
                    HttpStatus.BAD_REQUEST.value()) ;

            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }

        UploadSalesResponse response = carSalesService.uploadCsv(file);
        ApiResponse<UploadSalesResponse> apiResponse = getApiResponse(response);

        return ResponseEntity.ok(apiResponse);
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
        return new ApiResponse<>(success,message,response,HttpStatus.OK.value());
    }
    //for year api - count
    @GetMapping("/yearly-count")
    public ResponseEntity<?> getYearlyCount() {

        List<YearlyCountDto> carCounts  = carSalesService.getYearlyCarCounts();
        ApiResponse<List<YearlyCountDto>> response = new ApiResponse<>(
                true,
                "Data Read Successfully",
                carCounts,
                HttpStatus.OK.value());
                return ResponseEntity.ok(response);

    }

    // api for states wise sale count
    @GetMapping("state-count")
    public ResponseEntity<?> getStateWiseCarCounts() {
        List<StateCountDto> carCounts = carSalesService.getStateWiseCarCounts();
        ApiResponse<List<StateCountDto>> response = new ApiResponse<>(
                true,
                "Data Read Successfully",
                carCounts,
                HttpStatus.OK.value());
                return ResponseEntity.ok(response);
    }

    @GetMapping("/average-price")
    public ResponseEntity<?> getAveragePrice() {
        AveragePriceDto averagePriceDto = carSalesService.getAveragePrice();
        ApiResponse<AveragePriceDto> response = new ApiResponse<>(
                true,
                "Data Read Successfully",
                averagePriceDto,
                HttpStatus.OK.value()
        );
        return ResponseEntity.ok(response);
    }


    @GetMapping("/brand-count")
    public ResponseEntity<?> getBrandCount() {
        List<BrandCountDto> brandCountDos = carSalesService.getBrandCounts();
        ApiResponse<List<BrandCountDto>> response = new ApiResponse<>(
                true,
                "Data Read Successfully",
                brandCountDos,
                HttpStatus.OK.value()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/fuel-type-count")
    public ResponseEntity<?> getFuelTypeCont(){
        List<FuelTypeCountDto> fuelTypeCountDos = carSalesService.getFuelTypeCounts();
        ApiResponse<List<FuelTypeCountDto>> response = new ApiResponse<>(
                true,
                "Data Read Successfully",
                fuelTypeCountDos,
                HttpStatus.OK.value()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/payment-mode-count")
    public ResponseEntity<?> getPaymentModeCount(){
      List<PaymentModeCountDto> paymentModeCountDos = carSalesService.getPaymentModeCounts();
      ApiResponse<List<PaymentModeCountDto>> response = new ApiResponse<>(
              true,
              "Data Read successfully",
              paymentModeCountDos,
              HttpStatus.OK.value()
      );
      return ResponseEntity.ok(response);
    }

    @GetMapping("/model-count")
    public ResponseEntity<?> getModelCount(){
        List<ModelCountDto> modelCountDos = carSalesService.getModelCounts();
        ApiResponse<List<ModelCountDto>> response = new ApiResponse<>(
                true,
                "Data Read successfully",
                modelCountDos,
                HttpStatus.OK.value()
        );
        return ResponseEntity.ok(response);
    }
}


