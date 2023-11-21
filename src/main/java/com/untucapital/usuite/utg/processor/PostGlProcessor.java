package com.untucapital.usuite.utg.processor;

import com.untucapital.usuite.utg.dto.request.PostGLRequestDTO;
import com.untucapital.usuite.utg.entity.AccountEntity;
import com.untucapital.usuite.utg.entity.PostGl;
import com.untucapital.usuite.utg.model.transactions.TransactionInfo;
import com.untucapital.usuite.utg.service.cms.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.sql.Date;


@Component
@Slf4j
@AllArgsConstructor
public class PostGlProcessor {


    private final AccountService accountService;

    public PostGl createFromAccountRequest(TransactionInfo transactionInfo) {

        PostGl postGl = new PostGl();
        PostGLRequestDTO request = new PostGLRequestDTO();
        request.setTxDate(Date.valueOf(transactionInfo.getTransactionDate()));
        request.setReference(transactionInfo.getReference());
        request.setDescription(transactionInfo.getDescription());

        // Get the current date and time
        java.util.Date utilDate = new java.util.Date();

        // Convert the java.util.Date to java.sql.Date
        Date sqlDate = new Date(utilDate.getTime());
        request.setDTStamp(sqlDate);
        request.setICurrencyID(1);
        request.setId("JL");
        request.setICurrencyID(1);
        request.setFExchangeRate(1.0f);
        request.setBIsJCDocLine(false);
        request.setBIsSTGLDocLine(false);
        request.setBPrintCheque(false);
        request.setIInvLineID(0L);
        request.setBPBTPaid(false);
        request.setBReconciled(false);
        request.setUserName(transactionInfo.getUserName());
        request.setFExchangeRate(0F);
        request.setFForeignDebit(0F);
        request.setFForeignCredit(0F);
        request.setTaxTypeID(0);
        request.setTax_Amount(0F);
        request.setProject(0);
        request.setDrCrAccount(0);
        request.setJobCodeLink(0);
        request.setRepID(0);
        request.setFJCRepCost(0F);
        request.setIMFPID(0);
        request.setITxBranchID(0);
        request.setIGLTaxAccountID(0);
        request.setPostGL_iBranchID(0);
        request.setIImportDeclarationID(0);
        request.setIMajorIndustryCodeID(0);
        request.setFForeignTax(0F);


        AccountEntity accountEntity = accountService.findAccountByAccount(transactionInfo.getFromAccount());


        request.setCredit(transactionInfo.getAmount());
        request.setDebit(0f);
        request.setAccountLink(accountEntity.getAccountLink());

        BeanUtils.copyProperties(request, postGl);
        return postGl;
    }

    public PostGl createToAccountRequest(TransactionInfo transactionInfo) {

        PostGl postGl = new PostGl();
        PostGLRequestDTO request = new PostGLRequestDTO();
        request.setTxDate(Date.valueOf(transactionInfo.getTransactionDate()));
        request.setReference(transactionInfo.getReference());
        request.setDescription(transactionInfo.getDescription());

        // Get the current date and time
        java.util.Date utilDate = new java.util.Date();

        // Convert the java.util.Date to java.sql.Date
        Date sqlDate = new Date(utilDate.getTime());
        request.setDTStamp(sqlDate);
        request.setICurrencyID(1);
        request.setId("JL");
        request.setICurrencyID(1);
        request.setFExchangeRate(1.0f);
        request.setBIsJCDocLine(false);
        request.setBIsSTGLDocLine(false);
        request.setBPrintCheque(false);
        request.setIInvLineID(0L);
        request.setBPBTPaid(false);
        request.setBReconciled(false);
        request.setUserName(transactionInfo.getUserName());
        request.setFExchangeRate(0F);
        request.setFForeignDebit(0F);
        request.setFForeignCredit(0F);
        request.setTaxTypeID(0);
        request.setTax_Amount(0F);
        request.setProject(0);
        request.setDrCrAccount(0);
        request.setJobCodeLink(0);
        request.setRepID(0);
        request.setFJCRepCost(0F);
        request.setIMFPID(0);
        request.setITxBranchID(0);
        request.setIGLTaxAccountID(0);
        request.setPostGL_iBranchID(0);
        request.setIImportDeclarationID(0);
        request.setIMajorIndustryCodeID(0);
        request.setFForeignTax(0F);


        AccountEntity accountEntity = accountService.findAccountByAccount(transactionInfo.getToAccount());

        request.setCredit(0f);
        request.setDebit(transactionInfo.getAmount());
        request.setAccountLink(accountEntity.getAccountLink());

        BeanUtils.copyProperties(request, postGl);

        return postGl;
    }
}
