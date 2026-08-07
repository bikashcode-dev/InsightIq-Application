package com.InsightIQ.InsightIQ.service;

import com.InsightIQ.InsightIQ.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CarService {
    UploadSalesResponse uploadCsv(MultipartFile file);
    List<YearlyCountDto> getYearlyCarCounts();
    List<StateCountDto> getStateWiseCarCounts();
    AveragePriceDto getAveragePrice();
    List<BrandCountDto> getBrandCounts();
    List<FuelTypeCountDto> getFuelTypeCounts();
    List<PaymentModeCountDto> getPaymentModeCounts();
    List<ModelCountDto> getModelCounts();
}
