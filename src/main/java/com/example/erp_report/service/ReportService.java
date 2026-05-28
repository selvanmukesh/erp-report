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

    public List<Report> saveReport(List<ReportRequest> request) throws Exception {
        List<Report> saveAllReport = new ArrayList<>();
        for (ReportRequest item : request) {
            Report report = new Report();
            report.setId(item.getId());
            report.setName(item.getName());
            report.setOrderNo(item.getOrderNo());
            report.setLink(item.getLink());
            saveAllReport.add(report);
        }
        return reportRepository.saveAll(saveAllReport);
    }

    public List<Report> getAllReport() throws Exception {
        return reportRepository.findAll(
                Sort.by(Sort.Direction.ASC, "orderNo"));
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

    public String deleteById(Long id) throws Exception {
        reportRepository.deleteById(id);
        return "Updated Successfully";
    }

}
