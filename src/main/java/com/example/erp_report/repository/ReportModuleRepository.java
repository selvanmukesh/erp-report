package com.example.erp_report.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.erp_report.dto.ReportModuleProjection;
import com.example.erp_report.model.ReportModule;

@Repository
public interface ReportModuleRepository extends JpaRepository<ReportModule, Long> {

    @Query("select rm from  ReportModule rm where rm.impactModuleId is null")
    List<ReportModule> findAllReportModuleNotInInPactedModule();

    @Query(value = """
            SELECT
                r.report_name AS report_name,
                r.order_no AS report_order_no,
                rm.name AS module_name,
                rm.order_no AS module_order,
                rmn.name AS impact_module_name,
                rm.impact_module_id,
                rm.id AS module_id,
                r.id AS report_id
            FROM report r
            LEFT JOIN report_module rm ON r.id = rm.report_id
            LEFT JOIN report_module rmn ON rmn.id = rm.impact_module_id
            WHERE r.id=:id
            ORDER BY r.order_no, rm.order_no
            """, nativeQuery = true)

    List<ReportModuleProjection> findReportModuleById(@Param("id") Long id);

}
