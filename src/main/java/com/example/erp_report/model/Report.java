package com.example.erp_report.model;

import java.util.List;

import com.example.erp_report.common.audit.BaseEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "report")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "report_name", nullable = false, unique = true)
    private String name;

    @Column(name = "order_no", nullable = false, unique = true)
    private Long orderNo;

    @Column(name = "link", nullable = false, unique = true)
    private String link;

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ReportModule> reportModule;



}
