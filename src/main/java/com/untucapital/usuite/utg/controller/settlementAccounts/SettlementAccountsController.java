package com.untucapital.usuite.utg.controller.settlementAccounts;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.untucapital.usuite.utg.dto.musoni.savingsaccounts.ClientAccounts;
import com.untucapital.usuite.utg.model.transactions.interim.dto.TransactionDTO;
import com.untucapital.usuite.utg.service.MusoniService;
import com.untucapital.usuite.utg.service.aws.S3Service;
import com.untucapital.usuite.utg.service.pdfGeneratorService.LoanStatementPdfGeneratorService;
import com.untucapital.usuite.utg.service.settlementAccounts.SettlementAccountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("settlements/")
public class SettlementAccountsController {

    private final SettlementAccountsService settlementAccountsService;
    private final LoanStatementPdfGeneratorService loanStatementPdfGeneratorService;
    private final MusoniService musoniService;
    private final S3Service s3Service;

    @GetMapping("verifyToken/{clientId}/{token}")
    public boolean verifToken(@PathVariable("clientId") String clientId, @PathVariable("token") String token ){
        return settlementAccountsService.verifySettlementAccountsToken(clientId, token);
    }

    @GetMapping("/getLoanRepaymentSchedulePdf/{loanAccount}")
    public ResponseEntity<byte[]> getAmortizationSchedule(@PathVariable("loanAccount") String loanAccount) throws ParseException {
        ByteArrayInputStream bis = loanStatementPdfGeneratorService.generateAmortizationSchedulePdf(loanAccount);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=loan_statement.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(bis.readAllBytes());
    }

    @GetMapping("/getInterimStatementPdf/loanId/{loanId}")
    public ResponseEntity<byte[]> getInterimStatementPdf(
            @PathVariable("loanId") String loanId) throws ParseException, JsonProcessingException {

        // Call getClientAccountsByLoanAcc() to retrieve ClientAccounts details
        ClientAccounts clientAccounts = musoniService.getClientAccountsByLoanAcc(loanId);

        // Extract the necessary IDs from ClientAccounts
        int savingsId = Integer.parseInt(clientAccounts.getSettlementAccount());
        int postMaturityFeeId = Integer.parseInt(clientAccounts.getPostMaturityFee());

        // Generate the PDF using the retrieved IDs
        ByteArrayInputStream bis = loanStatementPdfGeneratorService.generateInterimStatementPdf(
                Integer.parseInt(clientAccounts.getLoanId()), savingsId, postMaturityFeeId);

        // Prepare HTTP headers for the response
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=interim_statement.pdf");

        // Return the generated PDF as the response
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(bis.readAllBytes());
    }



    @GetMapping("/getCombinedTransactions")
    public ResponseEntity<List<TransactionDTO>> getCombinedTransactions(
            @RequestParam int loanId,
            @RequestParam int savingsId,
            @RequestParam int postMaturityFeeId) throws JsonProcessingException {

        // Fetch combined transactions based on the service logic
        List<TransactionDTO> combinedTransactions = loanStatementPdfGeneratorService.getCombinedTransactions(loanId, savingsId, postMaturityFeeId);

        // Return the combined transactions as a ResponseEntity
        return ResponseEntity.ok(combinedTransactions);
    }

    @GetMapping("/getLoanStatement/{loanAccount}")
    public ResponseEntity<String> getSettlementStatement(@PathVariable("loanAccount") String loanId) throws ParseException {
        ByteArrayInputStream bis = loanStatementPdfGeneratorService.generateAmortizationSchedulePdf(loanId);

//        String key = "pdfs/" + loanId+"-"+RandomNumUtils.generateAwsCode(Integer.parseInt(loanId)) + ".pdf";
        String key = "pdfs/" + UUID.randomUUID() + ".pdf";
        String pdfUrl = s3Service.uploadPDF(key, bis.readAllBytes());
        return ResponseEntity.ok(pdfUrl);
    }
}
