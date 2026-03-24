package com.example.server.service;

import com.example.server.model.User;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class ExportService {

    // PDF Logic using iText
    public byte[] exportEmployeesToPdf(List<User> employees) throws Exception {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);
        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLUE);
        document.add(new Paragraph("Employee Directory", font));
        document.add(new Paragraph(" ")); // Spacer

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        
        // Headers
        table.addCell("ID");
        table.addCell("Full Name");
        table.addCell("Email");
        table.addCell("Role");

        for (User emp : employees) {
            table.addCell(emp.getEmpId());
            table.addCell(emp.getFirstName() + " " + emp.getLastName());
            table.addCell(emp.getEmail());
            table.addCell(emp.getRole().toString());
        }

        document.add(table);
        document.close();
        return out.toByteArray();
    }

    // Excel Logic using Apache POI
    public byte[] exportEmployeesToExcel(List<User> employees) throws Exception {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Employees");
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        String[] headers = {"ID", "First Name", "Last Name", "Email", "Role", "Location"};
        Row headerRow = sheet.createRow(0);

        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        int rowIdx = 1;
        for (User emp : employees) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(emp.getEmpId());
            row.createCell(1).setCellValue(emp.getFirstName());
            row.createCell(2).setCellValue(emp.getLastName());
            row.createCell(3).setCellValue(emp.getEmail());
            row.createCell(4).setCellValue(emp.getRole().toString());
            row.createCell(5).setCellValue(emp.getLocation());
        }

        workbook.write(out);
        workbook.close();
        return out.toByteArray();
    }
}