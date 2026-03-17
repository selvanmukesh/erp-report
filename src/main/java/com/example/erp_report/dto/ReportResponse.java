package com.example.erp_report.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ReportResponse {
    private Long id;
    private String name;
    private Long OrderNo;
    private String link;
}
