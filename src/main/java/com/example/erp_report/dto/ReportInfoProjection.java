package com.example.erp_report.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ReportInfoProjection {
    private String reportName;
    private String link;
    private String moduleName;
    private String impactedDashboard;
    private Long impactModuleId;
    private Long reportModuleId;

}
