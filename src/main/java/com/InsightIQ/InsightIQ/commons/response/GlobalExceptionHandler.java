package com.InsightIQ.InsightIQ.commons.response;


import com.InsightIQ.InsightIQ.dto.UploadSalesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<UploadSalesResponse>> handleException(Exception exception) {

        logger.error("Exception: Unexpected application error", exception);
        UploadSalesResponse response = new UploadSalesResponse(0, 0, 0);

        ApiResponse<UploadSalesResponse> apiResponse = new ApiResponse<UploadSalesResponse>(
                false,
                "Unexpected application error , Please try again later.",
                response,
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        return new ResponseEntity<ApiResponse<UploadSalesResponse>>(
                apiResponse,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
