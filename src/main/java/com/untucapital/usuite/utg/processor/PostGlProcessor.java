package com.untucapital.usuite.utg.processor;

import com.untucapital.usuite.utg.dto.request.PostGLRequestDTO;
import com.untucapital.usuite.utg.entity.res.AccountEntityResponseDTO;
import com.untucapital.usuite.utg.entity.res.PostGlResponseDTO;
import com.untucapital.usuite.utg.model.User;
import com.untucapital.usuite.utg.model.cms.Vault;
import com.untucapital.usuite.utg.model.transactions.TransactionInfo;
import com.untucapital.usuite.utg.repository.cms.VaultRepository;
import com.untucapital.usuite.utg.service.cms.AccountService;
import com.untucapital.usuite.utg.utils.EmailSender;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;


@Component
@Slf4j
@AllArgsConstructor
public class PostGlProcessor {


    private final AccountService accountService;
    private final VaultRepository vaultRepository;
    private final EmailSender emailSender;

    public PostGlResponseDTO createFromAccountRequest(TransactionInfo transactionInfo) {

        PostGlResponseDTO postGl = new PostGlResponseDTO();
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


        AccountEntityResponseDTO accountEntity = accountService.findAccountByAccount(transactionInfo.getFromAccount());


        request.setCredit(transactionInfo.getAmount());
        request.setDebit(0f);
        request.setAccountLink(accountEntity.getAccountLink());

        BeanUtils.copyProperties(request, postGl);
        return postGl;
    }

    public PostGlResponseDTO createToAccountRequest(TransactionInfo transactionInfo) {

        PostGlResponseDTO postGl = new PostGlResponseDTO();
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


        AccountEntityResponseDTO accountEntity = accountService.findAccountByAccount(transactionInfo.getToAccount());

        request.setCredit(0f);
        request.setDebit(transactionInfo.getAmount());
        request.setAccountLink(accountEntity.getAccountLink());

        BeanUtils.copyProperties(request, postGl);

        return postGl;
    }

    public void checkLimits(TransactionInfo request, Float currentBalanceFromAccount, Float currentBalanceToAccount, List<User> user ){



        if (currentBalanceFromAccount != null) {
            Optional<Vault> optionalVault = vaultRepository.findByAccount(request.getFromAccount());

            if(optionalVault.isPresent()) {
                Vault vault = optionalVault.get();

                BigDecimal vaultBalance = BigDecimal.valueOf(currentBalanceFromAccount).subtract(BigDecimal.valueOf(request.getAmount()));
                vault.setCurrentAmount(vaultBalance);

                vaultRepository.save(vault);
            }
        }

        if(currentBalanceToAccount != null) {

            Optional<Vault> optionalVault = vaultRepository.findByAccount(request.getFromAccount());

            if(optionalVault.isPresent()) {

                Vault vault = optionalVault.get();
                BigDecimal vaultBalance = BigDecimal.valueOf(currentBalanceToAccount).add(BigDecimal.valueOf(request.getAmount()));

                int value = BigDecimal.valueOf(currentBalanceToAccount).compareTo(vault.getMaxAmount());
                if(value<=0) {

                    int result = vault.getMaxAmount().compareTo(vaultBalance);
                    if (result < 0) {
                        //TODO Send email

                        sendEmail(
                                "Panashe" + " " + "Rutimhu",
                                "panasherutimhu0@gmail.com",
                                "Limit Exceeded",
                                "The balance of this Vault : (" + vault.getAccount() + ") has exceeded the limit.",
                                "Panashe" + " " + "Rutimhu"
                        );
                    } else {
                        vault.setCurrentAmount(vaultBalance);
                        vaultRepository.save(vault);
                    }
                }else {
                    vault.setCurrentAmount(vaultBalance);
                    vaultRepository.save(vault);
                }
            }
        }
    }


    private void sendEmail(String recipientName, String recipientEmail, String recipientSubject, String recipientMessage, String senderName) {
        String emailText = emailSender.limitExceeded(recipientName, recipientMessage, senderName);
        emailSender.send(recipientEmail, recipientSubject, emailText);
    }
}
