package com.InsightIQ.InsightIQ.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UploadSalesResponse {

    private int totalRecords;
    private int successFulCount;
    private int failedCount;
}
