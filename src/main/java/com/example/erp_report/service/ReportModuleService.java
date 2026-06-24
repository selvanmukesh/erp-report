package com.example.erp_report.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.erp_report.dto.ImpactModuleProjection;
import com.example.erp_report.dto.ReportInfoProjection;
import com.example.erp_report.dto.ReportModuleProjection;
import com.example.erp_report.dto.ReportModuleRequest;
import com.example.erp_report.model.Report;
import com.example.erp_report.model.ReportModule;
import com.example.erp_report.repository.ReportModuleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportModuleService {
    private final ReportModuleRepository reportModuleRepository;
    private final JdbcTemplate jdbcTemplate;

    public List<ReportModule> saveAllReportModule(List<ReportModuleRequest> request) throws Exception {
        List<ReportModule> reportModuleList = new ArrayList<>();
        for (ReportModuleRequest item : request) {
            ReportModule reportModule = new ReportModule();
            Report report = new Report();
            if (item.getReportId() != null) {
                report.setId(item.getReportId());
            }

            if (item.getId() != null) {
                reportModule.setId(item.getId());
            }

            reportModule.setName(item.getName());
            reportModule.setOrderNo(item.getOrderNo());
            reportModule.setImpactModuleId(item.getImpactModuleId());
            reportModule.setReport(report);
            reportModuleList.add(reportModule);
        }
        List<ReportModule> result = reportModuleRepository.saveAll(reportModuleList);
        return result;
    }

    public List<ImpactModuleProjection> findAllReportModuleNotInInPactedModule()
            throws Exception {

        // return reportModuleRepository.findAllReportModuleNotInInPactedModule();
        return reportModuleRepository.findByImpactModuleIdIsNull();

    }

    public List<ReportModule> findReportModuleById()
            throws Exception {

        return reportModuleRepository.findAllReportModuleNotInInPactedModule();

    }

    public List<ReportModuleProjection> findReportModuleById(Long id) throws Exception {
        return reportModuleRepository.findReportModuleById(id);
    }

    public String deleteById(Long id) throws Exception {
        reportModuleRepository.deleteById(id);
        return "Updated Successfully";
    }

    public List<ReportInfoProjection> getReportInfoProjection() {
        return reportModuleRepository.getReportInfoProjection();
    }

    public List<Map<String, Object>> getReportInfoProjectionTest() {
        List<String> moduleNames = jdbcTemplate.queryForList(
                "SELECT DISTINCT name FROM report_module where impact_module_id is null",
                String.class);
        String dynamicColumns = moduleNames.stream()
                .map(name -> "'" + name + "' AS `" + name + "`")
                .collect(Collectors.joining(", "));
        String sql = """
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
                    report_module_id,
                """ + dynamicColumns + """
                FROM report_cte
                ORDER BY report_order_id, report_module_order_id
                """;
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        result.stream().forEach(item -> {
            Map<String, Object> element = item;

            Object foundedModuleName = element.get(element.get("module_name"));
            Object impactDashboard = element.get(element.get("impacted_dashboard"));

            if (foundedModuleName instanceof String && element.get("impact_module_id") == null) {
                element.put((String) foundedModuleName, "Yes");
            } else if (impactDashboard instanceof String) {

                element.put((String) impactDashboard, "Yes");
            }
            Iterator<Map.Entry<String, Object>> finalCorrection = element.entrySet().iterator();
            // while (finalCorrection.hasNext()) {
            //     Object k1 = finalCorrection.next().getKey();
            //     if (!List.of("report_name", "module_name", "impact_module_id", "report_module_id", (String) foundedModuleName,
            //     (String) impactDashboard).contains((String)k1)) {

            //         k1.setValue(null);
            //     }
            // }

            // if(List.of("report_name","module_name","impact_module_id","report_module_id",foundedModuleName,impactDashboard).contains(element)){

            // }

            System.out.println("----" + element.get(element.get("module_name")));

        });

        // dynamicColumns

        return result;

        // return reportModuleRepository.getReportInfoProjectionTest();
    }
}
