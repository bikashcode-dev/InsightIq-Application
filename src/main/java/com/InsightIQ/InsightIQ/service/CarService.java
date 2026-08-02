package com.InsightIQ.InsightIQ.service;

import com.InsightIQ.InsightIQ.dto.UploadSalesResponse;
import org.springframework.web.multipart.MultipartFile;

public interface CarService {
    UploadSalesResponse uploadCsv(MultipartFile file);
}
