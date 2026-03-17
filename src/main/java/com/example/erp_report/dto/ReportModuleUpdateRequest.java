package com.example.erp_report.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReportModuleUpdateRequest {

    @NotNull(message = "Id is required")
    private Long id;

    @NotBlank(message = "Report name is required")
    private String name;

    @NotNull(message = "Order is required")
    private Long orderNo;

    private Long impactModuleId;

}
