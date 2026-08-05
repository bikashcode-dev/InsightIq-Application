package com.InsightIQ.InsightIQ.service;

import com.InsightIQ.InsightIQ.dto.UploadSalesResponse;
import com.InsightIQ.InsightIQ.dto.YearlyCountDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface CarService {
    UploadSalesResponse uploadCsv(MultipartFile file);
    List<YearlyCountDto> getYearlyCarCounts();
}
