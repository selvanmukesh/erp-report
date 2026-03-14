package com.example.erp_report.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.erp_report.common.ApiResponse;
import com.example.erp_report.dto.ReportRequest;
import com.example.erp_report.model.Report;
import com.example.erp_report.service.ReportService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/report")
@CrossOrigin(origins="*")
public class ReportController {
    @Autowired
    ReportService reportService;

    @PostMapping
    public ResponseEntity<ApiResponse<Report>> postMethodName(@Valid @RequestBody ReportRequest request) {
        try {
            Report report = reportService.saveReport(request);
            ApiResponse<Report> response = new ApiResponse<Report>(report, null, HttpStatus.CREATED.value(),
                    "Created Success Fully");

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            ApiResponse<Report> response = new ApiResponse<Report>(null, e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Report>>> getAllReport() {
        try {
            List<Report> report = reportService.getAllReport();
            ApiResponse<List<Report>> response = new ApiResponse<>(report, null, HttpStatus.CREATED.value(),
                    "Created Success Fully");

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            // TODO: handle exception
            ApiResponse<List<Report>> response = new ApiResponse<>(null, e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
