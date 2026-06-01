package com.example.erp_report.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.example.erp_report.dto.ReportInfoProjection;

@Service
public class ExcelExportService {

    public ByteArrayInputStream exportReportInfo(List<ReportInfoProjection> reports)
            throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Report");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("report_name");
        header.createCell(1).setCellValue("link");
        header.createCell(2).setCellValue("module_name");
        header.createCell(3).setCellValue("impacted_dashboard");
        header.createCell(4).setCellValue("impact_module_id");
        header.createCell(5).setCellValue("report_module_id");

        int rowNum = 1;

        for (ReportInfoProjection item : reports) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(Objects.toString(item.getReportName(), ""));
            row.createCell(1).setCellValue(Objects.toString(item.getLink(), ""));
            row.createCell(2).setCellValue(Objects.toString(item.getModuleName(), ""));
            row.createCell(3).setCellValue(Objects.toString(item.getImpactedDashboard(), ""));
            row.createCell(4).setCellValue(Objects.toString(item.getImpactModuleId(), ""));
            row.createCell(5).setCellValue(Objects.toString(item.getReportModuleId(), ""));
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();

        return new ByteArrayInputStream(out.toByteArray());
    }
}
