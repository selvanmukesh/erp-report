package com.example.erp_report.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReportModuleRequest {

    private Long id;

    @NotBlank(message = "Module name is required")
    private String name;

    @NotNull(message = "Order is required")
    private Long orderNo;

    private Long impactModuleId; // null allowed

    // @NotNull(message = "Report Id is required")
    private Long reportId;
}