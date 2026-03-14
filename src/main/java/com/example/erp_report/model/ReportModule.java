package com.example.erp_report.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "report_module")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReportModule  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "order_no", nullable = false, unique = false)
    private Long orderNo;

    @Column(name = "impact_module_id", nullable = true)
    private Long impactModuleId;

    @ManyToOne
    @JoinColumn(name = "report_id")
    @JsonBackReference
    private Report report;
}
