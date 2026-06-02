package com.example.erp_report.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.erp_report.common.ApiResponse;
import com.example.erp_report.dto.ImpactModuleProjection;
import com.example.erp_report.dto.ReportInfoProjection;
import com.example.erp_report.dto.ReportModuleProjection;
import com.example.erp_report.dto.ReportModuleRequest;
import com.example.erp_report.model.ReportModule;
import com.example.erp_report.service.ExcelExportService;
import com.example.erp_report.service.ReportModuleService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/reportModule")
public class ReportModuleController {
    @Autowired
    ReportModuleService reportModuleService;
    @Autowired
    private ExcelExportService excelExportService;

    @PostMapping
    public ResponseEntity<ApiResponse<List<ReportModule>>> saveAllReportModule(
            @Valid @RequestBody List<ReportModuleRequest> request) {
        try {

            List<ReportModule> reportList = reportModuleService.saveAllReportModule(request);
            ApiResponse<List<ReportModule>> response = new ApiResponse<List<ReportModule>>(reportList, null,
                    HttpStatus.CREATED.value(),
                    "Saved Success Fully");

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            ApiResponse<List<ReportModule>> response = new ApiResponse<>(null, e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ImpactModuleProjection>>> findAllReportModuleNotInInPactedModule() {
        try {
            List<ImpactModuleProjection> reportList = reportModuleService.findAllReportModuleNotInInPactedModule();
            ApiResponse<List<ImpactModuleProjection>> response = new ApiResponse<List<ImpactModuleProjection>>(
                    reportList, null,
                    HttpStatus.OK.value(),
                    "Success");

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<List<ImpactModuleProjection>> response = new ApiResponse<>(null, e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<List<ReportModuleProjection>>> findReportModuleById(@PathVariable Long id) {
        try {
            List<ReportModuleProjection> reportList = reportModuleService.findReportModuleById(id);
            ApiResponse<List<ReportModuleProjection>> response = new ApiResponse<List<ReportModuleProjection>>(
                    reportList, null,
                    HttpStatus.OK.value(),
                    "Success");

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<List<ReportModuleProjection>> response = new ApiResponse<>(null, e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteById(@PathVariable Long id) {
        try {
            String reportList = reportModuleService.deleteById(id);
            ApiResponse<String> response = new ApiResponse<String>(
                    reportList, null,
                    HttpStatus.CREATED.value(),
                    "Success");

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            ApiResponse<String> response = new ApiResponse<>(null, e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/reportInfo")
    public ResponseEntity<ApiResponse<List<ReportInfoProjection>>> getReportInfoProjection() {
        try {
            List<ReportInfoProjection> reportList = reportModuleService.getReportInfoProjection();
            ApiResponse<List<ReportInfoProjection>> response = new ApiResponse<List<ReportInfoProjection>>(
                    reportList, null,
                    HttpStatus.OK.value(),
                    "Success");

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<List<ReportInfoProjection>> response = new ApiResponse<>(null, e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/reportInfo/download")
    public ResponseEntity<InputStreamResource> downloadExcel()
            throws IOException {
        List<ReportInfoProjection> reportList = reportModuleService.getReportInfoProjection();

        // ByteArrayInputStream in = excelExportService.exportReportInfo(reportList);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(reportList);
        ByteArrayInputStream in = excelExportService.exportDataAsExcel(json);

        HttpHeaders headers = new HttpHeaders();
        headers.add(
                "Content-Disposition",
                "attachment; filename=reportInfo.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(
                        MediaType.parseMediaType(
                                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new InputStreamResource(in));
    }

}
