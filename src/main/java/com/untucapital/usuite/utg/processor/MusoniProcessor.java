package com.untucapital.usuite.utg.processor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.untucapital.usuite.utg.client.RestClient;
import com.untucapital.usuite.utg.commons.AppConstants;
import com.untucapital.usuite.utg.model.Employee;
import com.untucapital.usuite.utg.model.transactions.TransactionInfo;
import com.untucapital.usuite.utg.model.transactions.Transactions;
import com.untucapital.usuite.utg.repository2.PostGlRepository;
import com.untucapital.usuite.utg.utils.MusoniUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

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

    private final RestClient restClient;

    public List<TransactionInfo> createPastelTransaction(List<Transactions> transactions) throws ParseException, JsonProcessingException {

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
                if(transaction.getCurrency().getCode().equalsIgnoreCase("USD")){

                    transactionInfo.setCurrency(AppConstants.CURRENCY);

                }
                transactionInfo.setDescription(transaction.getType().getValue());

                if(transaction.getType().getValue().equalsIgnoreCase("disbursement")){

                    transactionInfo.setTransactionType(AppConstants.DISBURSEMENT);
                    transactionInfo.setReference("DIS-"+transaction.getId());

                } else if(transaction.getType().getValue().equalsIgnoreCase("repayment")){

                    transactionInfo.setTransactionType(AppConstants.REPAYMENT);
                    transactionInfo.setReference("REP-"+transaction.getId());
                }

                transactionInfo.setExchangeRate(1);

                //TODO put the actual dto and from account

                transactionInfo.setFromAccount(transaction.getSubmittedByUsername());

                /**
                 * Retrieve all Empoloyees from Musoni and loop through the list to get the office name where a transaction was initiated
                 */
                List<Employee> employees = restClient.getAllUsers();

                //filter out the employee who initiated the transaction
                Optional<Employee> initiator = employees.stream()
                        .filter(employee -> employee.getDisplayName().equals(transaction.getSubmittedByUsername()))
                        .findFirst();

                log.info("Employee who initiated the transaction: {}", initiator.toString());

                if(initiator.isPresent()) {
                    Employee employee = initiator.get();
                    String officeName = employee.getOfficeName();
                } else {
                    throw  new UsernameNotFoundException("The user with the username is not found in the database");
                }
                transactionInfo.setToAccount("Not Available");

                transactionInfoList.add(transactionInfo);
            }

        }

        return transactionInfoList;
    }
}
