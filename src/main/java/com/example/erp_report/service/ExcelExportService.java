package com.example.erp_report.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
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

        // Create bold font
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);

        // Create header style
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(headerFont);

        Row header = sheet.createRow(0);
        Cell cell0 = header.createCell(0);
        cell0.setCellValue("report_name");
        cell0.setCellStyle(headerStyle);

        Cell cell1 = header.createCell(1);
        cell1.setCellValue("link");
        cell1.setCellStyle(headerStyle);

        Cell cell2 = header.createCell(2);
        cell2.setCellValue("module_name");
        cell2.setCellStyle(headerStyle);

        Cell cell3 = header.createCell(3);
        cell3.setCellValue("impacted_dashboard");
        cell3.setCellStyle(headerStyle);

        Cell cell4 = header.createCell(4);
        cell4.setCellValue("impact_module_id");
        cell4.setCellStyle(headerStyle);

        Cell cell5 = header.createCell(5);
        cell5.setCellValue("report_module_id");
        cell5.setCellStyle(headerStyle);

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
        for (int i = 0; i < 6; i++) {
            sheet.autoSizeColumn(i);
            // sheet.setColumnWidth(i, sheet.getColumnWidth(i) + 20);
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();

        return new ByteArrayInputStream(out.toByteArray());
    }
}
