package com.InsightIQ.InsightIQ.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class UploadSalesResponse {

    private int totalRecords;       //Number of Records;
    private int successCount;      //    Number of successCount;
    private int failedCount;      //        Number of failedCount;
}
