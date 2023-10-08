package com.untucapital.usuite.utg.processor;

import com.untucapital.usuite.utg.model.transactions.TransactionInfo;
import com.untucapital.usuite.utg.model.transactions.Transactions;
import com.untucapital.usuite.utg.utils.MusoniUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class MusoniProcessor {

    public List<TransactionInfo> createPastelTransaction(List<Transactions> transactions) throws ParseException {

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
                transactionInfo.setCurrency(transaction.getCurrency().getName());
                //TODO put the actual description and transaction type
                transactionInfo.setDescription(transaction.getType().getValue());
                transactionInfo.setTransactionType(transaction.getType().getValue());
                transactionInfo.setReference(transaction.getOfficeName());
                transactionInfo.setExchangeRate("Not Available");
                transactionInfo.setFromAccount("Not Available");
                transactionInfo.setToAccount("Not Available");

                transactionInfoList.add(transactionInfo);
            }

        }

        return transactionInfoList;
    }
}
