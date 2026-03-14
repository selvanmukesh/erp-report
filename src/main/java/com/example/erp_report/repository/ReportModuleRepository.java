package com.example.erp_report.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.erp_report.model.ReportModule;

@Repository
public interface ReportModuleRepository extends JpaRepository<ReportModule, Long> {

    @Query("select rm from  ReportModule rm where rm.impactModuleId is null")
    List<ReportModule> findAllReportModuleNotInInPactedModule();
}
