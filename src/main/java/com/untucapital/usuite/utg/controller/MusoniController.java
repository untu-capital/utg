package com.untucapital.usuite.utg.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.untucapital.usuite.utg.DTO.DisbursedLoanMonth;
import com.untucapital.usuite.utg.DTO.DisbursedLoans;
import com.untucapital.usuite.utg.service.MusoniService;
import com.untucapital.usuite.utg.service.SmsService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.persistence.QueryHint;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.hibernate.annotations.QueryHints.READ_ONLY;
import static org.hibernate.jpa.QueryHints.HINT_CACHEABLE;
import static org.hibernate.jpa.QueryHints.HINT_FETCH_SIZE;

@AllArgsConstructor
@RestController
@RequestMapping(value ="musoni", produces="application/json")
@Component
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

    @Autowired
    MusoniService musoniService;

    @Autowired
    SmsService smsService;

    HttpHeaders headers;

    //    Get All Clients
    @GetMapping("clients")
    public String getAllClients() {

        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        return restTemplate.exchange(musoniUrl + "clients", HttpMethod.GET, entity, String.class).getBody();
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

    // Add years to a date in Java
    public Date addYears(Date date, int years) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, years);
        return cal.getTime();
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
    public String getClientLoans(@PathVariable String clientLoans) throws JSONException {
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        String clientAccount = restTemplate.exchange(musoniUrl + "clients/" + clientLoans + "/accounts", HttpMethod.GET, entity, String.class).getBody();

        for (int i = 0; i < new JSONObject(clientAccount).getJSONArray("loanAccounts").length(); i++) {
            String clientAccountsList = new JSONObject(new JSONObject(clientAccount).getJSONArray("loanAccounts").get(i).toString()).getString("accountNo");
            clientAccounts.add(clientAccountsList);
        }

        StringBuilder menu = new StringBuilder("");
        int num = 1;
        for (String x : clientAccounts) {
            menu.append("\n").append(num).append(". ").append(x);
            num++;
        }

        return menu.toString();
    }

    List<String> loanAcc = new ArrayList<>();
    //Get Clients Loans By Client ID
    @GetMapping("getLoanBalance/{loanAccount}")
    public Object getLoanBalance(@PathVariable String loanAccount) throws JSONException, JsonProcessingException {
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        String clientAccount = restTemplate.exchange(musoniUrl + "loans/"+ loanAccount, HttpMethod.GET, entity, String.class).getBody();

        Locale usa = new Locale("en", "US");
        NumberFormat currency = NumberFormat.getCurrencyInstance(usa);
        JSONObject jsonLoanSummary = new JSONObject(String.valueOf(new JSONObject(clientAccount)));

        String accountNo = jsonLoanSummary.getString("accountNo");
        String principalDisbursed = jsonLoanSummary.getJSONObject("summary").getString("principalDisbursed");
        String amountPaid = jsonLoanSummary.getJSONObject("summary").getString("principalPaid");
        String amountOverdue = jsonLoanSummary.getJSONObject("summary").getString("principalOverdue");
        String disbursmentDate = jsonLoanSummary.getJSONObject("timeline").getJSONArray("actualDisbursementDate").get(2).toString() + "-" + jsonLoanSummary.getJSONObject("timeline").getJSONArray("actualDisbursementDate").get(1).toString() + "-" + jsonLoanSummary.getJSONObject("timeline").getJSONArray("actualDisbursementDate").get(0).toString();
        String status = jsonLoanSummary.getJSONObject("status").getString("value");
        String numberOfRepayments = jsonLoanSummary.getString("numberOfRepayments");
        String maturityDate = jsonLoanSummary.getJSONObject("timeline").getJSONArray("expectedMaturityDate").get(2).toString() + "-" + jsonLoanSummary.getJSONObject("timeline").getJSONArray("expectedMaturityDate").get(1).toString() + "-" + jsonLoanSummary.getJSONObject("timeline").getJSONArray("expectedMaturityDate").get(0).toString();

        String loanBal =
                "{\n\"Mini Statement for Loan Account\":" + accountNo + "," +
                        "\n\n\"Loan Status\": " + "\"" + status + "\"" + "," +
                        "\n\"Loan Amount Disbursed\": " + principalDisbursed + "," +
                        "\n\"Disbursment Date\": " + "\"" + disbursmentDate + "\"" + "," +
                        "\n\"Amount Paid\": " + amountPaid + "," +
                        "\n\"Amount Overdue\": " + amountOverdue + "," +
                        "\n\"No. of Repayments\": " + numberOfRepayments + "," +
                        "\n\"Maturity Date\": " + "\"" + maturityDate + "\"" + "\n },";

        JSONObject json = new JSONObject(loanBal);
//        System.out.println(json.toString());
        return json.toString();
    }


    List<String> loanAccRepay = new ArrayList<>();
    //Get Clients Loans By Client ID
    @GetMapping("getLoanRepaymentSchedule/{loanAccount}")
    public Object getLoanRepaymentSchedule(@PathVariable String loanAccount) throws JSONException, JsonProcessingException {
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        String clientAccount = restTemplate.exchange(musoniUrl + "loans/" + loanAccount + "?associations=repaymentSchedule", HttpMethod.GET, entity, String.class).getBody();

        Locale usa = new Locale("en", "US");
        NumberFormat currency = NumberFormat.getCurrencyInstance(usa);

        JSONArray jsonLoanArray = new JSONArray(String.valueOf(new JSONObject(clientAccount).getJSONObject("repaymentSchedule").getJSONArray("periods")));

        loanAccRepay.clear();
        for (int i = 1; i < jsonLoanArray.length(); i++) {
            JSONObject jsonLoanSummary = new JSONObject(new JSONObject(clientAccount).getJSONObject("repaymentSchedule").getJSONArray("periods").get(i).toString());

            String period = jsonLoanSummary.getString("period");
            String fromDate = jsonLoanSummary.getJSONArray("fromDate").get(2).toString()+ "-" +jsonLoanSummary.getJSONArray("fromDate").get(1).toString()+"-"+jsonLoanSummary.getJSONArray("fromDate").get(0).toString();
            String dueDate = jsonLoanSummary.getJSONArray("dueDate").get(2).toString()+ "-" +jsonLoanSummary.getJSONArray("dueDate").get(1).toString()+"-"+jsonLoanSummary.getJSONArray("dueDate").get(0).toString();
            String amountDue = jsonLoanSummary.getString("totalDueForPeriod").toString();
            String amountPaid = jsonLoanSummary.getString("totalPaidForPeriod").toString();
            String amountOutstanding = jsonLoanSummary.getString("totalOutstandingForPeriod").toString();

            String loanBal = "{\n\"Prepayment Schedule for Loan Account\":" + "accountNo" + "," +
                    "\n\n\"Period\": " + "\"" + period + "\"" + "," +
                    "\n\"From Date\": " + fromDate + "," +
                    "\n\"To Date\": " + "\"" + dueDate + "\"" + "," +
                    "\n\"Amount Due\": " + amountDue + "," +
                    "\n\"Amount Paid\": " + amountPaid + "," +
                    "\n\"Amount Outstanding\": " + "\"" + amountOutstanding + "\"" + "\n }";
            JSONObject json = new JSONObject(loanBal);
//            System.out.println(json.toString());
            loanAccRepay.add(json.toString());
        }
        return loanAccRepay;
    }

    //    Get Loans By TimeStamp
    List<String> timestampedLoanAccs = new ArrayList<>();
//    @GetMapping("getLoansByTimestamp/{timestampa}")
    @Scheduled(fixedRate = 86400000L)
    public String getLoansByTimestamp() throws JSONException, ParseException, SQLException, IOException {
        Timestamp timestamp = (new Timestamp(System.currentTimeMillis()));
        long stamps = timestamp.getTime();
        String stampString = String.valueOf(stamps);
        String stamp = stampString.substring(0, stampString.length()-3);

        long timestamps = Long.valueOf(stamp) - 5864286L; // 2months, 1week is: 5864286
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        String timestampedLoanAcc = restTemplate.exchange(musoniUrl + "loans?modifiedSinceTimestamp="+timestamps, HttpMethod.GET, entity, String.class).getBody();

        timestampedLoanAccs.clear();
        for (int i = 0; i < new JSONObject(timestampedLoanAcc).getJSONArray("pageItems").length(); i++) {
            String loan_id = new JSONObject(new JSONObject(timestampedLoanAcc).getJSONArray("pageItems").get(i).toString()).getString("id");
            String status = new JSONObject(new JSONObject(timestampedLoanAcc).getJSONArray("pageItems").get(i).toString()).getJSONObject("status").getString("active");
            String client_id = new JSONObject(new JSONObject(timestampedLoanAcc).getJSONArray("pageItems").get(i).toString()).getString("clientId");
            String days_in_arrears = null;
            if (new JSONObject(new JSONObject(timestampedLoanAcc).getJSONArray("pageItems").get(i).toString()).has("summary")) {
                if (new JSONObject(new JSONObject(timestampedLoanAcc).getJSONArray("pageItems").get(i).toString()).getJSONObject("summary").has("daysInArrears")) {
                    days_in_arrears = new JSONObject(new JSONObject(timestampedLoanAcc).getJSONArray("pageItems").get(i).toString()).getJSONObject("summary").getString("daysInArrears");
                }
            }

            String phone_number = "0";
            if (new JSONObject(getClientById(client_id)).has("mobileNo")) {
//                phone_number = new JSONObject(getClientById(client_id)).getString("mobileNo");
                phone_number = "0775797299";
            }

            //            Send SMS when days in arrears: 1
            if (status == "true" && days_in_arrears != null && !days_in_arrears.isEmpty() && Integer.parseInt(days_in_arrears) == 1 && phone_number != "0"){
//                String sms_par_one = currencyFormatter(new BigDecimal(loanTransAmount)) + " is due and payable. Please make necessary arrangements to pay so that you maintain good record of your account. Kindly ignore this message if you have already made the FULL payment";
                String sms_par_one = "Your repayment amount is due and payable. Please make necessary arrangements to pay so that you maintain good record of your account. Kindly ignore this message if you have already made the FULL payment";
                smsService.sendSingle(phone_number, sms_par_one);
            }
            else if (status == "true" && days_in_arrears != null && !days_in_arrears.isEmpty() && Integer.parseInt(days_in_arrears) == 14 && phone_number != "0"){
                String sms_par_one = "We have not received your instalment payment in full and this is now 14 days in arrears. Please urgently make payment to avoid downgrading of your account and unnecessary penalties.";
                smsService.sendSingle(phone_number, sms_par_one);
            }
            else if (status == "true" && days_in_arrears != null && !days_in_arrears.isEmpty() && Integer.parseInt(days_in_arrears) == 30 && phone_number != "0"){
                String sms_par_one = "It is now 30 days without full payment of your instalment. To avoid being blacklisted and litigation please make urgent arrangements to settle account immediately.";
                smsService.sendSingle(phone_number, sms_par_one);
            }
            else {
                System.out.println("Client has no PAR OR Phonenumber is not available..");
            }

//            Send SMS to remind Clients of their Next repayment Dates.. 7 Days Before
//            musoniService.repaymentSchedule(phone_number, loan_id);

//
//            musoniService.disbursementSms(loan_id, phone_number, timestamps);

            StringBuilder menu = new StringBuilder("");
            String timestampObject = menu.append("{")
                    .append("\"loanId\"").append(" : ").append(loan_id).append(",")
                    .append("\"statusActive\"").append(" : ").append(status).append(",")
                    .append("\"daysInArrears\"").append(" : ").append(days_in_arrears).append(",")
                    .append("\"clientId\"").append(" : ").append(client_id).append(",")
                    .append("\"phoneNumber\"").append(" : ").append(phone_number)
                    .append("}").toString();
            System.out.println(loan_id);

            timestampedLoanAccs.add(timestampObject);
        }

        return timestampedLoanAccs.toString();
    }

    public String currencyFormatter(String amount) {
        Locale usa = new Locale("en", "US");
        NumberFormat currency = NumberFormat.getCurrencyInstance(usa);
        return currency.format(amount);
    }

    @GetMapping("getLoansByDisbursementDate/{fromDate}/{toDate}")
    public String getLoansByDisbursementDate(@PathVariable String fromDate, @PathVariable String toDate) throws JSONException {
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
