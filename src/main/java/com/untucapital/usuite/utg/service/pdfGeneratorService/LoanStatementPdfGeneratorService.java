package com.untucapital.usuite.utg.service.pdfGeneratorService;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.untucapital.usuite.utg.dto.client.repaymentSchedule.ClientStatementResponse;
import com.untucapital.usuite.utg.service.MusoniService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoanStatementPdfGeneratorService {

    private final MusoniService musoniService;

    public byte[] generateLoanSchedulePdf(List<Map<String, Object>> loanAccRepay) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        // Add a title
        document.add(new Paragraph("Client Loan Schedule").setFontSize(18).setBold().setTextAlignment(TextAlignment.CENTER));

        // Add table headers
        float[] columnWidths = {1, 2, 2, 2, 2};
        Table table = new Table(columnWidths);

        table.addHeaderCell(new Cell().add(new Paragraph("Date")).setBackgroundColor(ColorConstants.LIGHT_GRAY));
        table.addHeaderCell(new Cell().add(new Paragraph("Paid By")).setBackgroundColor(ColorConstants.LIGHT_GRAY));
        table.addHeaderCell(new Cell().add(new Paragraph("Total Due")).setBackgroundColor(ColorConstants.LIGHT_GRAY));
        table.addHeaderCell(new Cell().add(new Paragraph("Total Paid")).setBackgroundColor(ColorConstants.LIGHT_GRAY));
        table.addHeaderCell(new Cell().add(new Paragraph("Total Outstanding")).setBackgroundColor(ColorConstants.LIGHT_GRAY));

        // Add table data
        for (Map<String, Object> loanBal : loanAccRepay) {
            table.addCell(new Cell().add(new Paragraph((String) loanBal.get("date"))));
            table.addCell(new Cell().add(new Paragraph((String) loanBal.get("paidBy"))));
            table.addCell(new Cell().add(new Paragraph((String) loanBal.get("totalDue"))));
            table.addCell(new Cell().add(new Paragraph((String) loanBal.get("totalPaid"))));
            table.addCell(new Cell().add(new Paragraph((String) loanBal.get("totalOutstanding"))));
        }

        document.add(table);
        document.close();

        return baos.toByteArray();
    }


    public ByteArrayInputStream generateAmortizationSchedulePdf(String loanAccount) throws ParseException {

        List<ClientStatementResponse> clientStatementResponses = musoniService.getClientRepaymentSchedule(loanAccount);


        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            document.add(new Paragraph("Summarized Statement")
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                    .setFontSize(18));

            Table table = new Table(new float[]{1, 3, 3, 3, 3, 3});
            table.addHeaderCell("Period");
            table.addHeaderCell("Due Date");
            table.addHeaderCell("Paid By");
            table.addHeaderCell("Total Due");
            table.addHeaderCell("Total Paid");
            table.addHeaderCell("Total Outstanding");

            for (ClientStatementResponse entry : clientStatementResponses) {
                table.addCell(String.valueOf(entry.getPeriod()));
                table.addCell(String.valueOf(entry.getDueDate()));
                if(entry.getPaidBy() != null) {
                    table.addCell(String.valueOf(entry.getPaidBy()));
                }else {
                    table.addCell("- - -");
                }
                table.addCell(String.format("%.2f", entry.getAmountDue()));
                table.addCell(String.format("%.2f", entry.getAmountPaid()));
                table.addCell(String.format("%.2f", entry.getAmountOutstanding()));
            }

            document.add(table);
            document.close();

            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
