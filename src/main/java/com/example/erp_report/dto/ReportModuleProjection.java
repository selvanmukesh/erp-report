package com.example.erp_report.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class ReportModuleProjection {
    private String reportName;
    private Long reportOrderNo;
    private String moduleName;
    private Long moduleOrder;
    private String impactModuleName;
    private Long impactModuleId;
    private Long moduleId;
    private Long reportId;
}
