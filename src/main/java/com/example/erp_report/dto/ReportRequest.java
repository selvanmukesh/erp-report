package com.example.erp_report.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReportRequest {
    
    @NotBlank(message = "Report name is required")
    private String name;

    @NotNull(message = "Order is required")
    private Long orderNo;

    @NotBlank(message = "Report link is required")
    private String link;

}


