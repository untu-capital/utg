package com.untucapital.usuite.utg.service.pdfGeneratorService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;
import com.itextpdf.text.pdf.PdfPTable;
import com.untucapital.usuite.utg.client.RestClient;
import com.untucapital.usuite.utg.commons.AppConstants;
import com.untucapital.usuite.utg.dto.client.repaymentSchedule.ClientStatementResponse;
import com.untucapital.usuite.utg.model.transactions.interim.dto.SavingsTransactionDTO;
import com.untucapital.usuite.utg.model.transactions.interim.dto.TransactionDTO;
import com.untucapital.usuite.utg.model.transactions.interim.dto.TransactionTypeDTO;
import com.untucapital.usuite.utg.service.MusoniService;
import com.untucapital.usuite.utg.utils.MusoniUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoanStatementPdfGeneratorService {

    private final MusoniService musoniService;
    private final RestClient restClient;

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

            Table table = new Table(new float[]{3, 3, 3, 3, 3});
//            table.addHeaderCell("Period");
            table.addHeaderCell("Due Date");
            table.addHeaderCell("Paid By");
            table.addHeaderCell("Total Due");
            table.addHeaderCell("Total Paid");
            table.addHeaderCell("Total Outstanding");

            for (ClientStatementResponse entry : clientStatementResponses) {
//                table.addCell(String.valueOf(entry.getPeriod()));
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

    public List<TransactionDTO> getCombinedTransactions(int loanId, int savingsId, int postMaturityFeeId) throws JsonProcessingException {
        List<TransactionDTO> combinedTransactions = new ArrayList<>();

        // Get transactions by loan ID
        List<TransactionDTO> loanTransactions = musoniService.getTransactionsByLoanId(loanId);
        log.info("loanTransactions: {}", loanTransactions);
        combinedTransactions.addAll(loanTransactions);

        // Get the disbursement date
        LocalDate disbursementDate = restClient.getDisbursementDate(loanTransactions);
        LocalDate maturityDate = restClient.getMaturityDate(loanId);

        // Get transactions by savings ID
        List<SavingsTransactionDTO> savingsTransactions = musoniService.getTransactionsBySavingsId(savingsId);
        log.info("savingsTransactions: {}", savingsTransactions);

        // Get savings balance at the disbursement date
        TransactionDTO balanceBd = restClient.getSavingsBalanceBD(String.valueOf(savingsId), disbursementDate, savingsTransactions);
        log.info("balanceBd: {}", balanceBd);

        if (balanceBd != null) {
            TransactionTypeDTO transactionTypeDTO = new TransactionTypeDTO();
            transactionTypeDTO.setValue(AppConstants.BALANCE_BD); // Set the value of the TransactionTypeDTO
            balanceBd.setType(transactionTypeDTO); // Set the TransactionTypeDTO object in the balanceBd
            combinedTransactions.add(balanceBd); // Add the single TransactionDTO to the combined list
        }

        // Filter the savings transactions by date
        List<SavingsTransactionDTO> filteredSavingsTransactions = savingsTransactions.stream()
                .filter(savingsTransaction -> {
                    List<Integer> dateList = savingsTransaction.getDate();
                    LocalDate transactionDate = LocalDate.of(dateList.get(0), dateList.get(1), dateList.get(2));
                    return !transactionDate.isBefore(disbursementDate) && !transactionDate.isAfter(maturityDate);
                })
                .collect(Collectors.toList());

        // Convert the filtered transactions to TransactionDTO and add to the combined list
        combinedTransactions.addAll(convertToTransactionDTO(filteredSavingsTransactions));

        // Get transactions by post-maturity fee ID
        List<TransactionDTO> postMaturityFeeTransactions = musoniService.getTransactionsByPostMaturityFeeId(postMaturityFeeId);
        log.info("postMaturityFeeTransactions: {}", postMaturityFeeTransactions);
        combinedTransactions.addAll(postMaturityFeeTransactions);

        // Get and process loan repayment (penalty transactions)
        List<TransactionDTO> penaltyTransactions = musoniService.getAndProcessLoanRepayment(String.valueOf(loanId));
        log.info("penaltyTransactions: {}", penaltyTransactions);

        // Filter the penalty transactions by date
        List<TransactionDTO> filteredPenaltyTransactions = penaltyTransactions.stream()
                .filter(penaltyTransaction -> {
                    LocalDate transactionDate = penaltyTransaction.getDate();
                    return !transactionDate.isBefore(disbursementDate) && !transactionDate.isAfter(maturityDate);
                })
                .collect(Collectors.toList());

        // Add the filtered penalty transactions to the combined list
        combinedTransactions.addAll(filteredPenaltyTransactions);

        // Optionally, sort the combined transactions by date or another criterion
        combinedTransactions.sort(Comparator.comparing(TransactionDTO::getDate));

        return combinedTransactions;
    }

    private List<TransactionDTO> convertToTransactionDTO(List<SavingsTransactionDTO> savingsTransactions) {
        // Convert SavingsTransactionDTO to TransactionDTO
        return savingsTransactions.stream()
                .map(savingsTransaction -> {
                    TransactionDTO transactionDTO = new TransactionDTO();
                    transactionDTO.setId(savingsTransaction.getId());
                    transactionDTO.setType(savingsTransaction.getTransactionType());
                    LocalDate date = MusoniUtils.convertToLocalDate(savingsTransaction.getDate());
                    transactionDTO.setDate(date);
                    transactionDTO.setCurrency(savingsTransaction.getCurrency());
                    transactionDTO.setAmount(savingsTransaction.getAmount());
                    transactionDTO.setSubmittedByUsername(null); // Or set the appropriate value
                    // Set other fields as needed
                    return transactionDTO;
                })
                .collect(Collectors.toList());
    }

    public ByteArrayInputStream generateInterimStatementPdf(int loanId, int savingsId, int postMaturityFeeId) throws ParseException, JsonProcessingException {
        List<TransactionDTO> transactions = getCombinedTransactions(loanId, savingsId, postMaturityFeeId);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Add Header
            document.add(new Paragraph("Loan Statement")
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                    .setFontSize(18));

            document.add(new Paragraph("Account No: " + loanId)
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                    .setFontSize(12));

            document.add(new Paragraph("Interest Rate: 9.0%")
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                    .setFontSize(12));

            document.add(new Paragraph("Print Date: " + new SimpleDateFormat("dd.MM.yyyy").format(new Date()))
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                    .setFontSize(12));

            document.add(new Paragraph("\n"));

            // Add Table with width adjustments
            float[] columnWidths = {3, 3, 3, 3, 3}; // Adjust the number of columns and their relative widths
            Table table = new Table(columnWidths);
            table.setWidth(UnitValue.createPercentValue(100)); // Set the table to fill the width of the page

            // Add table headers
            table.addHeaderCell(new Cell().add(new Paragraph("Date")));
            table.addHeaderCell(new Cell().add(new Paragraph("Transaction Description")));
            table.addHeaderCell(new Cell().add(new Paragraph("Debit")));
            table.addHeaderCell(new Cell().add(new Paragraph("Credit")));
            table.addHeaderCell(new Cell().add(new Paragraph("Balance")));

            double balance = 0.0;

            // Iterate through transactions and populate the table
            for (TransactionDTO transaction : transactions) {
                table.addCell(new Cell().add(new Paragraph(String.valueOf(transaction.getDate()))));

                String transactionType = transaction.getType().getValue();
                if (transactionType.equalsIgnoreCase(AppConstants.LOAN_ACCRUAL)) {
                    transactionType = AppConstants.INTEREST_APPLIED;
                } else if (transactionType.equalsIgnoreCase(AppConstants.LOAN_DEPOSIT)) {
                    transactionType = AppConstants.LOAN_REPAYMENT;
                }
                table.addCell(new Cell().add(new Paragraph(transactionType)));

                if (transactionType.equalsIgnoreCase(AppConstants.LOAN_DISBURSEMENT) || transactionType.equalsIgnoreCase(AppConstants.INTEREST_APPLIED) || transactionType.equalsIgnoreCase(AppConstants.FEE_APPLIED) || transactionType.equalsIgnoreCase(AppConstants.PENATLY_FEE)) {
                    table.addCell(new Cell().add(new Paragraph(String.format("%.2f", transaction.getAmount()))));
                    table.addCell(new Cell().add(new Paragraph("0.00")));
                    balance += transaction.getAmount();
                } else if (transactionType.equalsIgnoreCase(AppConstants.LOAN_REPAYMENT) || transactionType.equalsIgnoreCase(AppConstants.BALANCE_BD)) {
                    table.addCell(new Cell().add(new Paragraph("0.00")));
                    table.addCell(new Cell().add(new Paragraph(String.format("%.2f", transaction.getAmount()))));
                    balance -= transaction.getAmount();
                } else {
                    table.addCell(new Cell().add(new Paragraph("0.00")));
                    table.addCell(new Cell().add(new Paragraph("0.00")));
                }

                table.addCell(new Cell().add(new Paragraph(String.format("%.2f", balance))));
            }

            document.add(table);
            document.close();

            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ByteArrayInputStream generateStyledStatementPdf(int loanId, int savingsId, int postMaturityFeeId) throws ParseException, JsonProcessingException {
        List<TransactionDTO> transactions = getCombinedTransactions(loanId, savingsId, postMaturityFeeId);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);


            File imageFile = new ClassPathResource("static/untu-logo.png").getFile();
            ImageData imageData = ImageDataFactory.create(imageFile.getAbsolutePath());

            if (!imageFile.exists()) {
                throw new IOException("Image file not found: " + imageFile.getAbsolutePath());
            }
            Image logo = new Image(imageData).scaleToFit(100, 100); // Scale the logo appropriately

            // Create a table for the header section
            Table headerTable = new Table(new float[]{2, 8});
            headerTable.setWidth(UnitValue.createPercentValue(100));

            // Add empty cells for layout
//            headerTable.addCell(new Cell().add(new Paragraph("Gweru")).setBorder(Border.NO_BORDER));
            headerTable.addCell(new Cell().add(logo).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE));
            headerTable.addCell(new Cell().setBorder(Border.NO_BORDER));

            // Second row for print date and account details
            headerTable.addCell(new Cell().setBorder(Border.NO_BORDER));
            Cell accountDetails = new Cell().add(new Paragraph("Print Date")
                            .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                            .setFontSize(10))
                    .add(new Paragraph(new SimpleDateFormat("dd.MM.yyyy").format(new Date()))
                            .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                            .setFontSize(10))
                    .add(new Paragraph("Account No.")
                            .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                            .setFontSize(10))
                    .add(new Paragraph(String.format("%08d", loanId)) // Formatting loanId as Account No.
                            .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                            .setFontSize(10))
                    .add(new Paragraph("Interest Rate")
                            .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                            .setFontSize(10))
                    .add(new Paragraph("9.0%")
                            .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                            .setFontSize(10))
                    .setBorder(Border.NO_BORDER)
                    .setTextAlignment(TextAlignment.LEFT);
            headerTable.addCell(accountDetails);

            // Third row for the account holder name
//            headerTable.addCell(new Cell(1, 3).add(new Paragraph("TGO Motors (Enard Masikati) Tinango")
//                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
//                    .setFontSize(12)).setBorder(Border.NO_BORDER));

            document.add(headerTable);

            // Add some space after the header
            document.add(new Paragraph("\n"));

            // Add Table with width adjustments
            float[] columnWidths = {3, 5, 3, 3, 3}; // Adjust the number of columns and their relative widths
            Table table = new Table(columnWidths);
            table.setWidth(UnitValue.createPercentValue(100)); // Set the table to fill the width of the page

            // Add table headers
            table.addHeaderCell(new Cell().add(new Paragraph("Date").setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))));
            table.addHeaderCell(new Cell().add(new Paragraph("Transaction Description").setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))));
            table.addHeaderCell(new Cell().add(new Paragraph("Debit").setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))));
            table.addHeaderCell(new Cell().add(new Paragraph("Credit").setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))));
            table.addHeaderCell(new Cell().add(new Paragraph("Balance").setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))));

            double balance = 0.0;

            // Iterate through transactions and populate the table
            for (TransactionDTO transaction : transactions) {
                table.addCell(new Cell().add(new Paragraph(String.valueOf(transaction.getDate()))));

                String transactionType = transaction.getType().getValue();
                if (transactionType.equalsIgnoreCase(AppConstants.LOAN_ACCRUAL)) {
                    transactionType = AppConstants.INTEREST_APPLIED;
                } else if (transactionType.equalsIgnoreCase(AppConstants.LOAN_DEPOSIT)) {
                    transactionType = AppConstants.LOAN_REPAYMENT;
                }
                table.addCell(new Cell().add(new Paragraph(transactionType)));

                if (transactionType.equalsIgnoreCase(AppConstants.LOAN_DISBURSEMENT) || transactionType.equalsIgnoreCase(AppConstants.INTEREST_APPLIED) || transactionType.equalsIgnoreCase(AppConstants.FEE_APPLIED) || transactionType.equalsIgnoreCase(AppConstants.PENATLY_FEE)) {
                    table.addCell(new Cell().add(new Paragraph(String.format("%.2f", transaction.getAmount()))));
                    table.addCell(new Cell().add(new Paragraph("0.00")));
                    balance += transaction.getAmount();
                } else if (transactionType.equalsIgnoreCase(AppConstants.LOAN_REPAYMENT) || transactionType.equalsIgnoreCase(AppConstants.BALANCE_BD)) {
                    table.addCell(new Cell().add(new Paragraph("0.00")));
                    table.addCell(new Cell().add(new Paragraph(String.format("%.2f", transaction.getAmount()))));
                    balance -= transaction.getAmount();
                } else {
                    table.addCell(new Cell().add(new Paragraph("0.00")));
                    table.addCell(new Cell().add(new Paragraph("0.00")));
                }

                table.addCell(new Cell().add(new Paragraph(String.format("%.2f", balance))));
            }

            // Add Contact Information and Address (Footer)
            document.add(new Paragraph("\n")); // Add some space before footer
            document.add(new Paragraph("Untu Capital (Pvt) Ltd\n79 ParkLane Building, \nJulius Nyerere Way, \nHarare")
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                    .setFontSize(10)
                    .setTextAlignment(TextAlignment.LEFT));

            document.add(new Paragraph("Email: info@untucapital.co.zw | Support: +263 784 558 769")
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                    .setFontSize(10)
                    .setTextAlignment(TextAlignment.LEFT));


            document.add(table);
            document.close();

            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
