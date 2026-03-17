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
                r.report_name AS reportName,
                r.order_no AS reportOrderNo,
                rm.name AS moduleName,
                rm.order_no AS moduleOrder,
                rmn.name AS impactModuleName,
                rm.impact_module_id AS impactModuleId,
                rm.id AS moduleId,
                r.id AS reportId
            FROM report r
            LEFT JOIN report_module rm ON r.id = rm.report_id
            LEFT JOIN report_module rmn ON rmn.id = rm.impact_module_id
            WHERE r.id=:id
            ORDER BY r.order_no, rm.order_no
            """, nativeQuery = true)

    List<ReportModuleProjection> findReportModuleById(@Param("id") Long id);
    List<ReportModule> findByImpactModuleIdIsNull();

}
