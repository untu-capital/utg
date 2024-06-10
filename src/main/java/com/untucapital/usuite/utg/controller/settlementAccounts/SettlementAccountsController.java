package com.untucapital.usuite.utg.controller.settlementAccounts;


import com.untucapital.usuite.utg.dto.client.repaymentSchedule.ClientStatementResponse;
import com.untucapital.usuite.utg.service.MusoniService;
import com.untucapital.usuite.utg.service.aws.S3Service;
import com.untucapital.usuite.utg.service.pdfGeneratorService.LoanStatementPdfGeneratorService;
import com.untucapital.usuite.utg.service.settlementAccounts.SettlementAccountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<byte[]> getAmortizationSchedule(@PathVariable("loanAccount") String loanAmount) throws ParseException {



        ByteArrayInputStream bis = loanStatementPdfGeneratorService.generateAmortizationSchedulePdf(loanAmount);


        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=loan_statement.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(bis.readAllBytes());
    }

    @GetMapping("/getLoanStatement/{loanAccount}")
    public ResponseEntity<String> getSettlementStatement(@PathVariable("loanAccount") String loanAmount) throws ParseException {



        ByteArrayInputStream bis = loanStatementPdfGeneratorService.generateAmortizationSchedulePdf(loanAmount);

        String key = "pdfs/" + UUID.randomUUID() + ".pdf";

        s3Service.uploadPDF(key, bis.readAllBytes());


        return ResponseEntity.ok(key);
    }




}
