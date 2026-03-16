package com.example.erp_report.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.erp_report.dto.ReportRequest;
import com.example.erp_report.dto.ReportResponse;
import com.example.erp_report.dto.ReportUpdateRequest;
import com.example.erp_report.model.Report;
import com.example.erp_report.repository.ReportRepository;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public Report saveReport(ReportRequest request) throws Exception {
        Report report = new Report();
        report.setName(request.getName());
        report.setOrderNo(request.getOrderNo());
        report.setLink(request.getLink());

        Report reportResponse = reportRepository.save(report);
        return reportResponse;
    }

    public List<Report> getAllReport() throws Exception {
        return reportRepository.findAll(
            Sort.by(Sort.Direction.ASC,"orderNo")
        );
    }

    public List<ReportResponse> getAllReportTableDataOnly() throws Exception {
        return reportRepository.getAllReport();
    }

    public String bulkUpdateReport(List<ReportUpdateRequest> updateRequest) throws Exception {
        List<Report> bulkUpdateData = new ArrayList<>();
        for (ReportUpdateRequest item : updateRequest) {
            Report report = new Report();
            report.setName(item.getName());
            report.setOrderNo(item.getOrderNo());
            report.setLink(item.getLink());
            report.setId(item.getId());
            bulkUpdateData.add(report);
        }
        reportRepository.saveAll(bulkUpdateData);
        return "Updated Success fully";

    }

}
