package com.example.erp_report.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ImpactModuleProjection {
    private Long id;
    private Long impactModuleId;
    private Long orderNo;
    private String name;
}
