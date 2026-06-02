package com.example.erp_report.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.erp_report.dto.ImpactModuleProjection;
import com.example.erp_report.dto.ReportInfoProjection;
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
                rm.order_no AS orderNo,
                rmn.name,
                rm.impact_module_id AS impactModuleId,
                rm.id,
                r.id AS reportId
            FROM report r
            LEFT JOIN report_module rm ON r.id = rm.report_id
            LEFT JOIN report_module rmn ON rmn.id = rm.impact_module_id
            WHERE r.id=:id
            AND rm.name IS NOT NULL
            ORDER BY r.order_no, rm.order_no
            """, nativeQuery = true)

    List<ReportModuleProjection> findReportModuleById(@Param("id") Long id);

    @Query(value = """
                        SELECT rm.id,
                   rm.impact_module_id,
                   rm.order_no,
                   Concat(rm.name, "(", r.report_name, ")") as name
            FROM   report_module rm
                   LEFT JOIN report r
                          ON rm.report_id = r.id
            WHERE  rm.impact_module_id IS NULL
            ORDER BY  LOWER(rm.name)
                        """, nativeQuery = true)
    List<ImpactModuleProjection> findByImpactModuleIdIsNull();

    @Query(value = """
                        WITH report_cte AS (
                SELECT
                    MIN(rm.id) OVER (PARTITION BY r.report_name) = rm.id AS unique_report_name_helper,
                    r.report_name,
                    r.link,
                    rm.name AS module_name,
                    (
                        SELECT rma.name
                        FROM report_module rma
                        WHERE rm.impact_module_id = rma.id
                    ) AS impacted_dashboard,
                    rm.id AS report_module_id,
                    rm.impact_module_id,
                    r.id AS report_id,
                    r.order_no AS report_order_id,
                    rm.order_no AS report_module_order_id
                FROM report_module rm
                LEFT JOIN report r
                    ON rm.report_id = r.id
            )
            SELECT
                report_name,
                CASE
                    WHEN unique_report_name_helper THEN link
                END AS link,
                module_name,
                impacted_dashboard,
                impact_module_id,
                report_module_id
            FROM report_cte
            ORDER BY report_order_id, report_module_order_id;
                        """, nativeQuery = true)
    List<ReportInfoProjection> getReportInfoProjection();

}
