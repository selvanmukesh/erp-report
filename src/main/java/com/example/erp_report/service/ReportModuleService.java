package com.example.erp_report.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.erp_report.dto.ReportModuleRequest;
import com.example.erp_report.model.Report;
import com.example.erp_report.model.ReportModule;
import com.example.erp_report.repository.ReportModuleRepository;

@Service
public class ReportModuleService {
    @Autowired
    private ReportModuleRepository reportModuleRepository;

    public List<ReportModule> saveAllReportModule(List<ReportModuleRequest> request) throws Exception {
        List<ReportModule> reportModuleList = new ArrayList<>();
        for (ReportModuleRequest item : request) {
            ReportModule reportModule = new ReportModule();
            Report report = new Report();
            report.setId(item.getReportId());
            reportModule.setName(item.getName());
            reportModule.setOrderNo(item.getOrderNo());
            reportModule.setImpactModuleId(item.getImpactModuleId());
            reportModule.setReport(report);
            reportModuleList.add(reportModule);
        }
        List<ReportModule> result = reportModuleRepository.saveAll(reportModuleList);
        return result;
    }

    public List<ReportModule> findAllReportModuleNotInInPactedModule()
            throws Exception {
        return reportModuleRepository.findAllReportModuleNotInInPactedModule();

    }

}
