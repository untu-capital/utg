
package com.untucapital.usuite.utg.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.untucapital.usuite.utg.dto.DisbursedLoans;
import com.untucapital.usuite.utg.dto.loans.Result;
import com.untucapital.usuite.utg.dto.response.PostGLResponseDTO;
import com.untucapital.usuite.utg.service.MusoniService;
import com.untucapital.usuite.utg.service.PostGlService;
import com.untucapital.usuite.utg.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.persistence.QueryHint;
import javax.security.auth.login.AccountNotFoundException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.hibernate.annotations.QueryHints.READ_ONLY;
import static org.hibernate.jpa.QueryHints.HINT_CACHEABLE;
import static org.hibernate.jpa.QueryHints.HINT_FETCH_SIZE;

@RestController
@RequestMapping(value ="musoni", produces="application/json")
@RequiredArgsConstructor
public class MusoniController {


    private static final Logger log = LoggerFactory.getLogger(MusoniPastelController.class);
    @Autowired
    RestTemplate restTemplate;
    @Value("${musoni.url}")
    private String musoniUrl;
    @Value("${musoni.username}")
    private String musoniUsername;
    @Value("${musoni.password}")
    private String musoniPassword;
    @Value("${musoni.X_FINERACT_PLATFORM_TENANTID}")
    private String musoniTenantId;
    @Value("${musoni.X_API_KEY}")
    private String musoniApiKey;

    private final PostGlService postGlService;


    public HttpHeaders httpHeaders() {
        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setBasicAuth(musoniUsername,musoniPassword);
        headers.set("X-Fineract-Platform-TenantId",musoniTenantId);
        headers.set("x-api-key",musoniApiKey);
        headers.set("Content-Type", "application/json");

        return headers;
    }

    private HttpEntity<String> setHttpEntity() {
        return new HttpEntity<String>(httpHeaders());
    }

    private final MusoniService musoniService;
    private final SmsService smsService;

    private HttpHeaders headers;

    //    Get All Clients
    @GetMapping("clients")
    public String getAllClients() {

        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        return restTemplate.exchange(musoniUrl + "clients", HttpMethod.GET, entity, String.class).getBody();
    }

    @GetMapping("postGl")
    public List<PostGLResponseDTO> getAllPostGl() {

        return postGlService.getAllPostGl();
    }

    //Get Client By Id
    @GetMapping("clients/{clientId}")
    public String getClientById(@PathVariable String clientId) {
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        return restTemplate.exchange(musoniUrl + "clients/"+clientId, HttpMethod.GET, entity, String.class).getBody();
    }

    //Get Client By Status
    @GetMapping("clients/status/{status}")
    public String getClientsByStatus(@PathVariable String status) {
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        return restTemplate.exchange(musoniUrl + "clients/"+status, HttpMethod.GET, entity, String.class).getBody();
    }

    //Get Loan By Id
    @GetMapping("loans/{loanId}")
    public String getLoanById(@PathVariable Long loanId) {
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        return restTemplate.exchange(musoniUrl + "loans/"+loanId, HttpMethod.GET, entity, String.class).getBody();
    }


    @GetMapping("loans/modifiedSinceTimestamp/{timeStamp}")
    public String getLoanByTimeStamp(@PathVariable Long timeStamp) {
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        return restTemplate.exchange(musoniUrl + "loans?modifiedSinceTimestamp="+timeStamp, HttpMethod.GET, entity, String.class).getBody();
    }

    @GetMapping("loans/transactions/")
    public ResponseEntity <Void> getTransactionsByTimestamp() throws ParseException, JsonProcessingException, AccountNotFoundException {
       musoniService.getLoansByTimestamp();

       return new ResponseEntity<>(HttpStatus.OK);
    }

    public static String[] getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        String endDate = dateFormat.format(cal.getTime());
        System.out.println("Current Date Time : " + endDate);
        cal.add(Calendar.YEAR, -1);
        String startDate = dateFormat.format(cal.getTime());
        System.out.println("Subtract one year from current date : " + startDate);

        return new String[] {startDate, endDate};

    }

    //Get All Loans
    @GetMapping("loans")
    @QueryHints(value = {
            @QueryHint(name = HINT_FETCH_SIZE, value = "" + Integer.MIN_VALUE),
            @QueryHint(name = HINT_CACHEABLE, value = "false"),
            @QueryHint(name = READ_ONLY, value = "true")
    })

    public String getLoans() {
        String[] dates = getDate();
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        return restTemplate.exchange(musoniUrl + "loans?disbursementFromDate=" + dates[0] + "&disbursementToDate=" + dates[1] + "&limit=2000&orderBy=id&sortOrder=DESC", HttpMethod.GET, entity, String.class).getBody();
    }

    //    Collect Transaction from Musoni
    @GetMapping("/getTransations/loanid/{loanId}/transactionId/{transactionId}")
    public String getMusoniPastelTrans(@PathVariable("loanId") String loanId, @PathVariable("transactionId") String transactionId) {

        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        return restTemplate.exchange(musoniUrl + "loans/" +loanId + "/transactions/" + transactionId, HttpMethod.GET, entity, String.class).getBody();
    }


    //Get All Client Loans By Id
    @GetMapping("clientAccounts/{clientId}")
    public String getClientLoansById(@PathVariable Long clientId) {
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        return restTemplate.exchange(musoniUrl + "clients/"+clientId+"/accounts", HttpMethod.GET, entity, String.class).getBody();
    }

    //Get Loan Repayment Schedule By Id
    @GetMapping("loansRepaymentSchedule/{loanId}")
    public String getLoanLoanRepaymentScheduleById(@PathVariable Long loanId) {
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        return restTemplate.exchange(musoniUrl + "loans/"+loanId+"?associations=repaymentSchedule", HttpMethod.GET, entity, String.class).getBody();
    }

    //Get Clients List By Search Filter
    @GetMapping("clientsSearchFilter/{searchFilter}")
    public String getClientListBySearchFilter(@PathVariable String searchFilter) {
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        return restTemplate.exchange(musoniUrl + "clients?search="+searchFilter, HttpMethod.GET, entity, String.class).getBody();
    }

    //Get ClientID by PhoneNumber
    @PostMapping("datafilters")
    public String getClientIDByDataFilter(@RequestBody Map<String, Object> body) {
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        return restTemplate.exchange(musoniUrl + "datafilters/clients/queries/run-filter", HttpMethod.GET, entity, String.class).getBody();
    }

    List<String> clientAccounts = new ArrayList<>();
    //Get Clients Loans By Client ID
    @GetMapping("getClientLoans/{clientLoans}")
    public String getClientLoans(@PathVariable String clientLoans) throws JsonProcessingException {
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders());
        String clientAccount = restTemplate.exchange(musoniUrl + "clients/" + clientLoans + "/accounts", HttpMethod.GET, entity, String.class).getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode clientAccountJson = objectMapper.readTree(clientAccount);
        JsonNode loanAccountsArray = clientAccountJson.get("loanAccounts");

        List<String> clientAccounts = new ArrayList<>();
        for (JsonNode loanAccount : loanAccountsArray) {
            String accountNo = loanAccount.get("accountNo").asText();
            clientAccounts.add(accountNo);
        }

        StringBuilder menu = new StringBuilder("");
        int num = 1;
        for (String account : clientAccounts) {
            menu.append("\n").append(num).append(". ").append(account);
            num++;
        }

        return menu.toString();
    }


    List<String> loanAcc = new ArrayList<>();
    //Get Clients Loans By Client ID
    @GetMapping("getLoanBalance/{loanAccount}")
    public Object getLoanBalance(@PathVariable String loanAccount) throws JsonProcessingException {
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders());
        String clientAccount = restTemplate.exchange(musoniUrl + "loans/" + loanAccount, HttpMethod.GET, entity, String.class).getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode clientAccountJson = objectMapper.readTree(clientAccount);

        Locale usa = new Locale("en", "US");
        NumberFormat currency = NumberFormat.getCurrencyInstance(usa);

        String accountNo = clientAccountJson.get("accountNo").asText();
        String principalDisbursed = clientAccountJson.at("/summary/principalDisbursed").asText();
        String amountPaid = clientAccountJson.at("/summary/principalPaid").asText();
        String amountOverdue = clientAccountJson.at("/summary/principalOverdue").asText();
        String disbursmentDate = String.format(
                "%s-%s-%s",
                clientAccountJson.at("/timeline/actualDisbursementDate/2").asText(),
                clientAccountJson.at("/timeline/actualDisbursementDate/1").asText(),
                clientAccountJson.at("/timeline/actualDisbursementDate/0").asText()
        );
        String status = clientAccountJson.at("/status/value").asText();
        String numberOfRepayments = clientAccountJson.get("numberOfRepayments").asText();
        String maturityDate = String.format(
                "%s-%s-%s",
                clientAccountJson.at("/timeline/expectedMaturityDate/2").asText(),
                clientAccountJson.at("/timeline/expectedMaturityDate/1").asText(),
                clientAccountJson.at("/timeline/expectedMaturityDate/0").asText()
        );

        Map<String, Object> loanBal = new HashMap<>();
        loanBal.put("Mini Statement for Loan Account", accountNo);
        loanBal.put("Loan Status", status);
        loanBal.put("Loan Amount Disbursed", principalDisbursed);
        loanBal.put("Disbursment Date", disbursmentDate);
        loanBal.put("Amount Paid", amountPaid);
        loanBal.put("Amount Overdue", amountOverdue);
        loanBal.put("No. of Repayments", numberOfRepayments);
        loanBal.put("Maturity Date", maturityDate);

        return loanBal;
    }

    List<String> loanAccRepay = new ArrayList<>();
    //Get Clients Loans By Client ID
    @GetMapping("getLoanRepaymentSchedule/{loanAccount}")
    public Object getLoanRepaymentSchedule(@PathVariable String loanAccount) throws JsonProcessingException {
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders());
        String clientAccount = restTemplate.exchange(
                musoniUrl + "loans/" + loanAccount + "?associations=repaymentSchedule",
                HttpMethod.GET,
                entity,
                String.class
        ).getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode clientAccountJson = objectMapper.readTree(clientAccount);
        JsonNode repaymentSchedule = clientAccountJson.at("/repaymentSchedule/periods");

        List<Map<String, Object>> loanAccRepay = new ArrayList<>();

        for (JsonNode periodNode : repaymentSchedule) {
            String period = periodNode.get("period").asText();
            String fromDate = formatDate(periodNode.get("fromDate"));
            String dueDate = formatDate(periodNode.get("dueDate"));
            String amountDue = periodNode.at("/totalDueForPeriod").asText();
            String amountPaid = periodNode.at("/totalPaidForPeriod").asText();
            String amountOutstanding = periodNode.at("/totalOutstandingForPeriod").asText();

            Map<String, Object> loanBal = new HashMap<>();
            loanBal.put("Prepayment Schedule for Loan Account", "accountNo");
            loanBal.put("Period", period);
            loanBal.put("From Date", fromDate);
            loanBal.put("To Date", dueDate);
            loanBal.put("Amount Due", amountDue);
            loanBal.put("Amount Paid", amountPaid);
            loanBal.put("Amount Outstanding", amountOutstanding);

            loanAccRepay.add(loanBal);
        }

        return loanAccRepay;
    }

    private String formatDate(JsonNode dateNode) {
        String year = dateNode.get(2).asText();
        String month = dateNode.get(1).asText();
        String day = dateNode.get(0).asText();
        return year + "-" + month + "-" + day;
    }


    //    Get Loans By TimeStamp
    List<String> timestampedLoanAccs = new ArrayList<>();
//    @GetMapping("getLoansByTimestamp/{timestampa}")
@GetMapping("getLoansByTimestamp")
public Object getLoansByTimestamp() throws JsonProcessingException {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    long stamps = timestamp.getTime();
    String stampString = String.valueOf(stamps);
    String stamp = stampString.substring(0, stampString.length() - 3);

    long timestamps = Long.valueOf(stamp) - 5864286L; // 2 months, 1 week is: 5864286
    HttpEntity<String> entity = new HttpEntity<>(httpHeaders());
    String timestampedLoanAcc = restTemplate.exchange(
            musoniUrl + "loans?modifiedSinceTimestamp=" + timestamps,
            HttpMethod.GET,
            entity,
            String.class
    ).getBody();

    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode loanAccJson = objectMapper.readTree(timestampedLoanAcc);
    JsonNode pageItems = loanAccJson.at("/pageItems");

    List<Map<String, Object>> timestampedLoanAccs = new ArrayList<>();

    for (JsonNode pageItem : pageItems) {
        String loan_id = pageItem.get("id").asText();
        String status = pageItem.at("/status/active").asText();
        String client_id = pageItem.get("clientId").asText();
        String days_in_arrears = null;

        if (pageItem.has("summary") && pageItem.at("/summary").has("daysInArrears")) {
            days_in_arrears = pageItem.at("/summary/daysInArrears").asText();
        }

        String phone_number = "0";

        String clientByIdResponse = getClientById(client_id); // Assuming getClientById returns a JSON string
        JsonNode clientById = objectMapper.readTree(clientByIdResponse);

        if (clientById.has("mobileNo")) {
            phone_number = clientById.get("mobileNo").asText();
        }

        if ("true".equals(status) && days_in_arrears != null && !days_in_arrears.isEmpty()) {
            int daysInArrears = Integer.parseInt(days_in_arrears);

            if (phone_number != "0") {
                String sms_par_one = "";

                if (daysInArrears == 1) {
                    sms_par_one = "Your repayment amount is due and payable. Please make necessary arrangements to pay so that you maintain a good record of your account. Kindly ignore this message if you have already made the FULL payment.";
                } else if (daysInArrears == 14) {
                    sms_par_one = "We have not received your installment payment in full, and this is now 14 days in arrears. Please urgently make payment to avoid downgrading of your account and unnecessary penalties.";
                } else if (daysInArrears == 30) {
                    sms_par_one = "It is now 30 days without full payment of your installment. To avoid being blacklisted and litigation, please make urgent arrangements to settle the account immediately.";
                }

                if (!sms_par_one.isEmpty()) {
                    smsService.sendSingle(phone_number, sms_par_one);
                }
            }
        } else {
            System.out.println("Client has no PAR or Phone number is not available.");
        }

        Map<String, Object> loanObject = new HashMap<>();
        loanObject.put("loanId", loan_id);
        loanObject.put("statusActive", status);
        loanObject.put("daysInArrears", days_in_arrears);
        loanObject.put("clientId", client_id);
        loanObject.put("phoneNumber", phone_number);

        System.out.println(loan_id);
        timestampedLoanAccs.add(loanObject);
    }

    return timestampedLoanAccs;
}

    public String currencyFormatter(String amount) {
        Locale usa = new Locale("en", "US");
        NumberFormat currency = NumberFormat.getCurrencyInstance(usa);
        return currency.format(amount);
    }

    @GetMapping("getLoansByDisbursementDate/{fromDate}/{toDate}")
    public Result getLoansByDisbursementDate(@PathVariable String fromDate, @PathVariable String toDate) {
        return  musoniService.disbursedLoans(fromDate,toDate);
    }

    @GetMapping("loans/disbursed-by-range/{fromDate}/{toDate}")
    public DisbursedLoans getLoansDisbursedByDateRange(@PathVariable String fromDate, @PathVariable String toDate) {
        log.info(String.valueOf(musoniService));
        return musoniService.getLoansDisbursedByDateRange(fromDate, toDate);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("loans/disbursed-by-range/{branchName}/{fromDate}/{toDate}")
    public DisbursedLoans findDisbursedLoansByRangeAndBranch(@PathVariable String branchName, @PathVariable String fromDate, @PathVariable String toDate) {
        log.info(String.valueOf(musoniService));
        return musoniService.findDisbursedLoansByRangeAndBranch(branchName, fromDate, toDate);
    }

}
