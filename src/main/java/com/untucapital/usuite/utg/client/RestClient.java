package com.untucapital.usuite.utg.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.untucapital.usuite.utg.model.Employee;
import com.untucapital.usuite.utg.model.Staff;
import com.untucapital.usuite.utg.model.transactions.PageItem;
import com.untucapital.usuite.utg.model.transactions.Loans;
import com.untucapital.usuite.utg.model.transactions.Transactions;
import com.untucapital.usuite.utg.utils.MusoniUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class RestClient {

    @Value("${musoni.url}")
    private String baseUrl;

    @Value("${musoni.username}")
    private String username;

    @Value("${musoni.X_API_KEY}")
    private String apiKey;

    @Value("${musoni.password}")
    private String password;

    @Value("${musoni.X_FINERACT_PLATFORM_TENANTID}")
    private String xFineractPlatformTenantId;

    private final RestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public RestClient(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
    }

    public HttpHeaders httpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setBasicAuth(username, password);
        headers.set("X-Fineract-Platform-TenantId", xFineractPlatformTenantId);
        headers.set("x-api-key", apiKey);
        headers.set("Content-Type", "application/json");

        return headers;
    }

    private HttpEntity<String> setHttpEntity() {
        return new HttpEntity<String>(httpHeaders());
    }

    public Loans getLoans(Long timestamp) {
        log.info("Calling musoni to get loans");
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        Loans loans = new Loans();

        try {
            String loanString = restTemplate.exchange(baseUrl + "loans?modifiedSinceTimestamp=" + MusoniUtils.generateTimestamp(timestamp), HttpMethod.GET, entity, String.class).getBody();
            log.info("Loans in the past 24 hours: {}", loanString);

            loans = objectMapper.readValue(loanString, Loans.class);

            log.info("Loans object: {}", loans);
        }catch (Exception e){
            log.info("Exception: {}", e.getMessage());
        }

        return loans;
    }

    public List<Transactions> getTransactions(int loanId) {

        log.info("PageItem Id :{}", loanId);
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        PageItem pageItem = new PageItem();

        try {
            String loanString = restTemplate.exchange(baseUrl + "loans/" + loanId + "?associations=transactions", HttpMethod.GET, entity, String.class).getBody();

            log.info("PageItem with id " + loanId + " and transaction information: {}", loanString);

            pageItem = objectMapper.readValue(loanString, PageItem.class);

            log.info("Transaction information for a loan: {}", pageItem);
        }catch (Exception e){
            log.info("Failed to get Loans: {}", e.getMessage());
        }

        List<Transactions> transactions = pageItem.getTransactions();

        if (transactions == null){

            return null;
        }

        log.info("Transactions : {}", transactions.toString());

        List<Transactions> cashTransactions = new ArrayList<>();
        for (Transactions tx : transactions) {
            if (tx.getType().isDisbursement() || tx.getType().isRepayment()) {

                cashTransactions.add(tx);
            }
            log.info("Transaction with repayment or disbursement: {}",cashTransactions.toString());
        }
        log.info("Transaction with repayment or disbursement: {}",cashTransactions.toString());

        return cashTransactions;

    }

    public List<Employee> getAllUsers() {
        log.info("Calling musoni to get staff");
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        List<Employee> employees = new ArrayList<Employee>();
        try {
            String staffString = restTemplate.exchange(baseUrl + "users", HttpMethod.GET, entity, String.class).getBody();
            log.info("Employees in the system: {}", staffString);

            Employee[] employeeList = objectMapper.readValue(staffString, Employee[].class);

            employees = List.of(employeeList);

            log.info("Loans object: {}", Arrays.toString(employeeList));
        }catch(Exception e){
            log.info("Failed to get Employees", e.getMessage());
        }

        return employees;
    }

}
