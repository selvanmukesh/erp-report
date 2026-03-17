package com.example.erp_report.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.erp_report.dto.ReportResponse;
import com.example.erp_report.model.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    @Query("SELECT r.id, r.name, r.orderNo,r.link FROM Report r order by r.orderNo ")
    List<ReportResponse> getAllReport();

}
