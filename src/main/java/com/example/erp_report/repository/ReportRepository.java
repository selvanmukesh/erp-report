package com.example.erp_report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.erp_report.model.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

}
