package com.untucapital.usuite.utg.processor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.untucapital.usuite.utg.client.RestClient;
import com.untucapital.usuite.utg.commons.AppConstants;
import com.untucapital.usuite.utg.entity.AccountEntity;
import com.untucapital.usuite.utg.entity.PostGl;
import com.untucapital.usuite.utg.exception.VaultNotFoundException;
import com.untucapital.usuite.utg.model.Employee;
import com.untucapital.usuite.utg.model.cms.Vault;
import com.untucapital.usuite.utg.model.transactions.TransactionInfo;
import com.untucapital.usuite.utg.model.transactions.Transactions;
import com.untucapital.usuite.utg.repository2.AccountsRepository;
import com.untucapital.usuite.utg.repository2.PostGlRepository;
import com.untucapital.usuite.utg.service.cms.AccountService;
import com.untucapital.usuite.utg.service.cms.VaultService;
import com.untucapital.usuite.utg.utils.MusoniUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.security.auth.login.AccountNotFoundException;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@AllArgsConstructor
public class MusoniProcessor {

    private final PostGlRepository postGlRepository;

    private final AccountsRepository accountsRepository;

    private final RestClient restClient;

    private final VaultService vaultService;

    private final AccountService accountService;

    public List<TransactionInfo> createPastelTransaction(List<Transactions> transactions) throws ParseException, JsonProcessingException, AccountNotFoundException {

        log.info("Transactions:{}", transactions.toString());

        List<TransactionInfo> transactionInfoList = new ArrayList<>();

        for (Transactions transaction : transactions) {

            int[] dateArray = transaction.getSubmittedOnDate();
            log.info("Date Array: {}", dateArray.toString());

            boolean isTransactionRequired = MusoniUtils.isValidDate(dateArray);

            if (isTransactionRequired) {

                LocalDate formattedDate = MusoniUtils.formatDate(dateArray);


                log.info("Formatted date:{}", formattedDate.toString());

                TransactionInfo transactionInfo = new TransactionInfo();


                transactionInfo.setAmount(transaction.getAmount());
                transactionInfo.setTransactionDate(formattedDate);
                if (transaction.getCurrency().getCode().equalsIgnoreCase("USD")) {

                    transactionInfo.setCurrency(AppConstants.CURRENCY);

                }
                transactionInfo.setDescription(transaction.getType().getValue());

                if (transaction.getType().getValue().equalsIgnoreCase("disbursement")) {


                    transactionInfo.setTransactionType(AppConstants.DISBURSEMENT);
                    transactionInfo.setReference("DIS-" + transaction.getId());

                } else if (transaction.getType().getValue().equalsIgnoreCase("repayment")) {

                    transactionInfo.setTransactionType(AppConstants.REPAYMENT);
                    transactionInfo.setReference("REP-" + transaction.getId());
                }

                transactionInfo.setExchangeRate(1);

                //TODO put the actual dto and from account

                AccountEntity accountEntity = getAccountLink(transaction);

                if (transaction.getType().getValue().equalsIgnoreCase("disbursement")) {

                    transactionInfo.setFromAccount(accountEntity.getAccountLink());
                    transactionInfo.setToAccount(AppConstants.LOAN_BOOK);

                } else if (transaction.getType().getValue().equalsIgnoreCase("repayment")) {

                    transactionInfo.setFromAccount(AppConstants.LOAN_BOOK);
                    transactionInfo.setToAccount(accountEntity.getAccountLink());
                }


                transactionInfoList.add(transactionInfo);
            }

        }

        return transactionInfoList;
    }


    public List<PostGl> setPostGlFields(List<Transactions> transactions) throws ParseException, JsonProcessingException, AccountNotFoundException {


        log.info("Transactions:{}", transactions.toString());

        List<PostGl> postGlList = new ArrayList<>();

        for (Transactions transaction : transactions) {

            int[] dateArray = transaction.getSubmittedOnDate();
            log.info("Date Array: {}", dateArray.toString());

            boolean isTransactionRequired = MusoniUtils.isValidDate(dateArray);

            if (isTransactionRequired) {

                LocalDate formattedDate = MusoniUtils.formatDate(dateArray);

                Date date = Date.valueOf(formattedDate);

                log.info("Formatted date:{}", formattedDate.toString());


                PostGl postGl = new PostGl();

                postGl.setTxDate(date);
                // Get the current date and time
                java.util.Date utilDate = new java.util.Date();

                // Convert the java.util.Date to java.sql.Date
                Date sqlDate = new Date(utilDate.getTime());
                postGl.setDTStamp(sqlDate);
                postGl.setId("JL");
                postGl.setICurrencyID(1);
                postGl.setFExchangeRate(1.0f);
                postGl.setDescription(transaction.getType().getValue());
                postGl.setBIsJCDocLine(false);
                postGl.setBIsSTGLDocLine(false);
                postGl.setBPrintCheque(false);
                postGl.setIInvLineID(0L);
                postGl.setBPBTPaid(false);
                postGl.setBReconciled(false);
                postGl.setUserName(transaction.getSubmittedByUsername());
                postGl.setFExchangeRate(0F);
                postGl.setFForeignDebit(0F);
                postGl.setFForeignCredit(0F);
                postGl.setTaxTypeID(0);
                postGl.setTax_Amount(0F);
                postGl.setProject(0);
                postGl.setDrCrAccount(0);
                postGl.setJobCodeLink(0);
                postGl.setRepID(0);
                postGl.setFJCRepCost(0F);
                postGl.setIMFPID(0);
                postGl.setITxBranchID(0);
                postGl.setIGLTaxAccountID(0);
                postGl.setPostGL_iBranchID(0);
                postGl.setIImportDeclarationID(0);
                postGl.setIMajorIndustryCodeID(0);

                AccountEntity entity = getAccountLink(transaction);

                if (transaction.getType().getValue().equalsIgnoreCase("disbursement")) {

                    postGl.setReference("DIS-" + transaction.getId());
                    postGl.setCredit((float) transaction.getAmount());
                    postGl.setDebit(0f);
                    postGl.setAccountLink(entity.getAccountLink());


                } else if (transaction.getType().getValue().equalsIgnoreCase("repayment")) {

                    postGl.setReference("REP-" + transaction.getId());
                    postGl.setCredit(0f);
                    postGl.setDebit((float) transaction.getAmount());
                    postGl.setAccountLink(entity.getAccountLink());

                }

                postGlList.add(postGl);
            }

        }

        return postGlList;
    }


    public List<PostGl> setPostGlClientLoanBook(List<Transactions> transactions) throws ParseException, JsonProcessingException, AccountNotFoundException {


        List<PostGl> postGlList = new ArrayList<>();

        for (Transactions transaction : transactions) {

            int[] dateArray = transaction.getSubmittedOnDate();


            boolean isTransactionRequired = MusoniUtils.isValidDate(dateArray);

            if (isTransactionRequired) {

                LocalDate formattedDate = MusoniUtils.formatDate(dateArray);

                Date date = Date.valueOf(formattedDate);

                PostGl postGl = new PostGl();
                PostGl postGlLoanBook = new PostGl();

                postGl.setTxDate(date);
                // Get the current date and time
                java.util.Date utilDate = new java.util.Date();

                // Convert the java.util.Date to java.sql.Date
                Date sqlDate = new Date(utilDate.getTime());
                postGl.setDTStamp(sqlDate);
                postGl.setId("JL");
                postGl.setICurrencyID(1);
                postGl.setFExchangeRate(1.0f);
                postGl.setDescription(transaction.getType().getValue());
                postGl.setBIsJCDocLine(false);
                postGl.setBIsSTGLDocLine(false);
                postGl.setBPrintCheque(false);
                postGl.setIInvLineID(0L);
                postGl.setBPBTPaid(false);
                postGl.setBReconciled(false);
                postGl.setUserName(transaction.getSubmittedByUsername());
                postGl.setFExchangeRate(0F);
                postGl.setFForeignDebit(0F);
                postGl.setFForeignCredit(0F);
                postGl.setTaxTypeID(0);
                postGl.setTax_Amount(0F);
                postGl.setProject(0);
                postGl.setDrCrAccount(0);
                postGl.setJobCodeLink(0);
                postGl.setRepID(0);
                postGl.setFJCRepCost(0F);
                postGl.setIMFPID(0);
                postGl.setITxBranchID(0);
                postGl.setIGLTaxAccountID(0);
                postGl.setPostGL_iBranchID(0);
                postGl.setIImportDeclarationID(0);
                postGl.setIMajorIndustryCodeID(0);

                AccountEntity entity = getAccountLink(transaction);

                if (transaction.getType().getValue().equalsIgnoreCase("disbursement")) {

                    postGl.setReference("DIS-" + transaction.getId());
                    postGl.setCredit(0f);
                    postGl.setDebit((float) transaction.getAmount());
                    postGl.setAccountLink(AppConstants.LOAN_BOOK_ACCOUNT);

                } else if (transaction.getType().getValue().equalsIgnoreCase("repayment")) {

                    postGl.setReference("REP-" + transaction.getId());
                    postGl.setCredit((float) transaction.getAmount());
                    postGl.setDebit(0f);
                    postGl.setAccountLink(AppConstants.LOAN_BOOK_ACCOUNT);

                }

                postGlList.add(postGl);
            }

        }

        return postGlList;
    }


    /**
     * Retrieve all Empoloyees from Musoni and loop through the list to get the office name where a transaction was initiated
     */

    public AccountEntity getAccountLink(Transactions transaction) throws JsonProcessingException, AccountNotFoundException {

        List<Employee> employees = restClient.getAllUsers();
        AccountEntity accountEntity = new AccountEntity();

        //filter out the employee who initiated the transaction
        if (employees.isEmpty()) {
            throw new NullPointerException("There are no users in the database");
        }

        log.info("Transaction initiator username:{}", transaction.getSubmittedByUsername());
        Optional<Employee> initiator = employees.stream()
                .filter(employee -> employee.getUsername().equals(transaction.getSubmittedByUsername()))
                .findFirst();

        if(initiator.isEmpty()) {
             throw new UsernameNotFoundException("The user with this username: {} is not in the system");
        }

        log.info("Employee who initiated the transaction: {}", initiator.toString());

        if (initiator.isPresent()) {
            Vault vault = new Vault();
            Employee employee = initiator.get();
            String officeName = employee.getOfficeName();
            String subString = " Teller Account";

            if (officeName.contains(subString)) {
                vault = vaultService.getVaultsByBranchAndType(officeName, AppConstants.VAULT_TYPE);
            } else {
                vault = vaultService.getVaultsByBranchAndType(officeName+subString, AppConstants.VAULT_TYPE);
            }


            if (vault == null) {
                throw new VaultNotFoundException("Vault not found");
            }
            log.info("Vault :{}", vault.toString());

            String accountName = vault.getAccount();

            accountEntity = accountService.findAccountByAccount(accountName);
            log.info("Account :{}", accountEntity);

        } else {
            throw new UsernameNotFoundException("The user with the username is not found in the database");
        }

        return accountEntity;
    }
}
