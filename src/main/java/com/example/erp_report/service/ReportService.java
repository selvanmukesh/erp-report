package com.example.erp_report.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.erp_report.dto.ReportRequest;
import com.example.erp_report.model.Report;
import com.example.erp_report.repository.ReportRepository;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public Report saveReport(ReportRequest request) throws Exception {
        Report report = new Report(null, null, null, null);
        report.setName(request.getName());
        report.setOrderNo(request.getOrderNo());

        Report reportResponse = reportRepository.save(report);
        return reportResponse;
    }
    public List<Report> getAllReport() throws Exception {
        return reportRepository.findAll();
    }
}
