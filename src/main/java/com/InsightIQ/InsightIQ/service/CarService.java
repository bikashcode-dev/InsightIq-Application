package com.InsightIQ.InsightIQ.service;

import com.InsightIQ.InsightIQ.dto.UploadSalesResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface CarService {
    UploadSalesResponse uploadCsv(MultipartFile file);
}
