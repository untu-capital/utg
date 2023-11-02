package com.untucapital.usuite.utg.processor;

import com.untucapital.usuite.utg.DTO.request.PostGLRequestDTO;
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

//
//    public List<TransactionInfo> createPastelPostGlTransaction(List<Transactions> transactions) throws ParseException, JsonProcessingException, AccountNotFoundException {
//
//        log.info("Transactions:{}", transactions.toString());
//
//        List<TransactionInfo> transactionInfoList = new ArrayList<>();
//
//        for (Transactions transaction : transactions) {
//
//            int[] dateArray = transaction.getSubmittedOnDate();
//            log.info("Date Array: {}", dateArray.toString());
//
//            boolean isTransactionRequired = MusoniUtils.isValidDate(dateArray);
//
//            if (isTransactionRequired) {
//
//                LocalDate formattedDate = MusoniUtils.formatDate(dateArray);
//
//
//                log.info("Formatted date:{}", formattedDate.toString());
//
//                TransactionInfo transactionInfo = new TransactionInfo();
//
//                transactionInfo.setAmount((float) transaction.getAmount());
//                transactionInfo.setTransactionDate(formattedDate);
//                if (transaction.getCurrency().getCode().equalsIgnoreCase("USD")) {
//
//                    transactionInfo.setCurrency(AppConstants.CURRENCY);
//
//                }
//                transactionInfo.setDescription(transaction.getType().getValue());
//
//                if (transaction.getType().getValue().equalsIgnoreCase("disbursement")) {
//
//
//                    transactionInfo.setTransactionType(AppConstants.DISBURSEMENT);
//                    transactionInfo.setReference("DIS-" + transaction.getId());
//
//                } else if (transaction.getType().getValue().equalsIgnoreCase("repayment")) {
//
//                    transactionInfo.setTransactionType(AppConstants.REPAYMENT);
//                    transactionInfo.setReference("REP-" + transaction.getId());
//                }
//
//                transactionInfo.setExchangeRate(1);
//
//                //TODO put the actual dto and from account
//                String submittedUsername = transaction.getSubmittedByUsername();
//
//                AccountEntity accountEntity = musoniProcessor.getAccountLink(submittedUsername);
//
//                if (transaction.getType().getValue().equalsIgnoreCase("disbursement")) {
//
//                    transactionInfo.setFromAccount(accountEntity.getAccountLink());
//                    transactionInfo.setToAccount(AppConstants.LOAN_BOOK);
//
//                } else if (transaction.getType().getValue().equalsIgnoreCase("repayment")) {
//
//                    transactionInfo.setFromAccount(AppConstants.LOAN_BOOK);
//                    transactionInfo.setToAccount(accountEntity.getAccountLink());
//                }
//
//
//                transactionInfoList.add(transactionInfo);
//            }
//
//        }
//
//        return transactionInfoList;
//    }

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

    log.info("postgl account: {}", transactionInfo.getFromAccount());

        AccountEntity accountEntity = accountService.findAccountByAccount(transactionInfo.getFromAccount());
        log.info("accountEntity: {}", accountEntity);

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
