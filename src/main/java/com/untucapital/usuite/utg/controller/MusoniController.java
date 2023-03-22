
package com.untucapital.usuite.utg.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.untucapital.usuite.utg.service.MusoniService;
import com.untucapital.usuite.utg.service.SmsService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.persistence.QueryHint;
import java.io.IOException;
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
public class MusoniController {
    @Autowired
    RestTemplate restTemplate;

    public HttpHeaders httpHeaders(){
        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setBasicAuth("kelvinr","Team@123");
        headers.set("X-Fineract-Platform-TenantId","untucapital");
        headers.set("x-api-key","4WEowWNz169UbYC052Lgsagd8U32t7As2lPYzEZl");
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

    private static final Logger log = LoggerFactory.getLogger(MusoniPastelController.class);

    //    Get All Clients
    @GetMapping("clients")
    public String getAllClients() {

        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        return restTemplate.exchange("https://api.demo.irl.musoniservices.com/v1/clients", HttpMethod.GET, entity, String.class).getBody();
    }

    //Get Client By Id
    @GetMapping("clients/{clientId}")
    public String getClientById(@PathVariable String clientId) {
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        return restTemplate.exchange("https://api.demo.irl.musoniservices.com/v1/clients/"+clientId, HttpMethod.GET, entity, String.class).getBody();
    }

    //Get Client By Status
    @GetMapping("clients/status/{status}")
    public String getClientsByStatus(@PathVariable String status) {
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        return restTemplate.exchange("https://api.demo.irl.musoniservices.com/v1/clients/"+status, HttpMethod.GET, entity, String.class).getBody();
    }

    //Get Loan By Id
    @GetMapping("loans/{loanId}")
    public String getLoanById(@PathVariable Long loanId) {
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        return restTemplate.exchange("https://api.demo.irl.musoniservices.com/v1/loans/"+loanId, HttpMethod.GET, entity, String.class).getBody();
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
        return restTemplate.exchange("https://api.demo.irl.musoniservices.com/v1/loans?disbursementFromDate=" + dates[0] + "&disbursementToDate=" + dates[1] + "&limit=2000&orderBy=id&sortOrder=DESC", HttpMethod.GET, entity, String.class).getBody();
    }

    //    Collect Transaction from Musoni
    @GetMapping("/getTransations/loanid/{loanId}/transactionId/{transactionId}")
    public String getMusoniPastelTrans(@PathVariable("loanId") String loanId, @PathVariable("transactionId") String transactionId) {

        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        return restTemplate.exchange("https://api.demo.irl.musoniservices.com/v1/loans/" +loanId + "/transactions/" + transactionId, HttpMethod.GET, entity, String.class).getBody();
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
        return restTemplate.exchange("https://api.demo.irl.musoniservices.com/v1/clients/"+clientId+"/accounts", HttpMethod.GET, entity, String.class).getBody();
    }

    //Get Loan Repayment Schedule By Id
    @GetMapping("loansRepaymentSchedule/{loanId}")
    public String getLoanLoanRepaymentScheduleById(@PathVariable Long loanId) {
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        return restTemplate.exchange("https://api.demo.irl.musoniservices.com/v1/loans/"+loanId+"?associations=repaymentSchedule", HttpMethod.GET, entity, String.class).getBody();
    }

    //Get Clients List By Search Filter
    @GetMapping("clientsSearchFilter/{searchFilter}")
    public String getClientListBySearchFilter(@PathVariable String searchFilter) {
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        return restTemplate.exchange("https://api.demo.irl.musoniservices.com/v1/clients?search="+searchFilter, HttpMethod.GET, entity, String.class).getBody();
    }

    //Get ClientID by PhoneNumber
    @PostMapping("datafilters")
    public String getClientIDByDataFilter(@RequestBody Map<String,Object> body ) {
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        return restTemplate.exchange("https://api.demo.irl.musoniservices.com/v1/datafilters/clients/queries/run-filter", HttpMethod.GET, entity, String.class).getBody();
    }

//    String miniStatement = enquiriesService.getMiniStatement(musoniService.getClientLoans(user.get().getMusoniClientId()).get(Integer.parseInt(levels[3])-1));
//            menu.append("END ").append(miniStatement);


    List<String> clientAccounts = new ArrayList<>();
    //Get Clients Loans By Client ID
    @GetMapping("getClientLoans/{clientLoans}")
    public String getClientLoans(@PathVariable String clientLoans) throws JSONException {
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        String clientAccount = restTemplate.exchange("https://api.demo.irl.musoniservices.com/v1/clients/" + clientLoans + "/accounts", HttpMethod.GET, entity, String.class).getBody();

        for (int i = 0; i<new JSONObject(clientAccount).getJSONArray("loanAccounts").length(); i++) {
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
        String clientAccount = restTemplate.exchange("https://api.demo.irl.musoniservices.com/v1/loans/"+ loanAccount, HttpMethod.GET, entity, String.class).getBody();

        Locale usa = new Locale("en", "US");
        NumberFormat currency = NumberFormat.getCurrencyInstance(usa);
        JSONObject jsonLoanSummary = new JSONObject(String.valueOf(new JSONObject(clientAccount)));

        String accountNo = jsonLoanSummary.getString("accountNo");
        String principalDisbursed = jsonLoanSummary.getJSONObject("summary").getString("principalDisbursed");
        String amountPaid = jsonLoanSummary.getJSONObject("summary").getString("principalPaid");
        String amountOverdue = jsonLoanSummary.getJSONObject("summary").getString("principalOverdue");
        String disbursmentDate = jsonLoanSummary.getJSONObject("timeline").getJSONArray("actualDisbursementDate").get(2).toString()+ "-" +jsonLoanSummary.getJSONObject("timeline").getJSONArray("actualDisbursementDate").get(1).toString()+"-"+jsonLoanSummary.getJSONObject("timeline").getJSONArray("actualDisbursementDate").get(0).toString();
        String status = jsonLoanSummary.getJSONObject("status").getString("value");
        String numberOfRepayments = jsonLoanSummary.getString("numberOfRepayments");
        String maturityDate = jsonLoanSummary.getJSONObject("timeline").getJSONArray("expectedMaturityDate").get(2).toString()+ "-" +jsonLoanSummary.getJSONObject("timeline").getJSONArray("expectedMaturityDate").get(1).toString()+"-"+jsonLoanSummary.getJSONObject("timeline").getJSONArray("expectedMaturityDate").get(0).toString();

        String loanBal =
                "{\n\"Mini Statement for Loan Account\":"+ accountNo +","+
                        "\n\n\"Loan Status\": " + "\"" + status + "\"" +","+
                        "\n\"Loan Amount Disbursed\": " + principalDisbursed +","+
                        "\n\"Disbursment Date\": " + "\"" + disbursmentDate + "\"" +","+
                        "\n\"Amount Paid\": " + amountPaid +","+
                        "\n\"Amount Overdue\": " + amountOverdue +","+
                        "\n\"No. of Repayments\": " + numberOfRepayments +","+
                        "\n\"Maturity Date\": " + "\"" + maturityDate + "\"" + "\n }," ;

        JSONObject json = new JSONObject(loanBal);
//        System.out.println(json.toString());
        return json.toString();
    }


    List<String> loanAccRepay = new ArrayList<>();
    //Get Clients Loans By Client ID
    @GetMapping("getLoanRepaymentSchedule/{loanAccount}")
    public Object getLoanRepaymentSchedule(@PathVariable String loanAccount) throws JSONException, JsonProcessingException {
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        String clientAccount = restTemplate.exchange("https://api.demo.irl.musoniservices.com/v1/loans/"+ loanAccount +"?associations=repaymentSchedule", HttpMethod.GET, entity, String.class).getBody();

        Locale usa = new Locale("en", "US");
        NumberFormat currency = NumberFormat.getCurrencyInstance(usa);

        JSONArray jsonLoanArray = new JSONArray(String.valueOf(new JSONObject(clientAccount).getJSONObject("repaymentSchedule").getJSONArray("periods")));

        loanAccRepay.clear();
        for (int i = 1; i<jsonLoanArray.length(); i++){
            JSONObject jsonLoanSummary = new JSONObject(new JSONObject(clientAccount).getJSONObject("repaymentSchedule").getJSONArray("periods").get(i).toString());

            String period = jsonLoanSummary.getString("period");
            String fromDate = jsonLoanSummary.getJSONArray("fromDate").get(2).toString()+ "-" +jsonLoanSummary.getJSONArray("fromDate").get(1).toString()+"-"+jsonLoanSummary.getJSONArray("fromDate").get(0).toString();
            String dueDate = jsonLoanSummary.getJSONArray("dueDate").get(2).toString()+ "-" +jsonLoanSummary.getJSONArray("dueDate").get(1).toString()+"-"+jsonLoanSummary.getJSONArray("dueDate").get(0).toString();
            String amountDue = jsonLoanSummary.getString("totalDueForPeriod").toString();
            String amountPaid = jsonLoanSummary.getString("totalPaidForPeriod").toString();
            String amountOutstanding = jsonLoanSummary.getString("totalOutstandingForPeriod").toString();

            String loanBal = "{\n\"Prepayment Schedule for Loan Account\":"+ "accountNo" +","+
                    "\n\n\"Period\": " + "\"" + period + "\"" +","+
                    "\n\"From Date\": " + fromDate +","+
                    "\n\"To Date\": " + "\"" + dueDate + "\"" +","+
                    "\n\"Amount Due\": " + amountDue +","+
                    "\n\"Amount Paid\": " + amountPaid +","+
                    "\n\"Amount Outstanding\": " + "\"" + amountOutstanding + "\"" + "\n }" ;
            JSONObject json = new JSONObject(loanBal);
//            System.out.println(json.toString());
            loanAccRepay.add(json.toString());
        }
        return loanAccRepay;
    }

    //    Get Loans By TimeStamp
    List<String> timestampedLoanAccs = new ArrayList<>();
    @GetMapping("getLoansByTimestamp/{timestampa}")
    public String getLoansByTimestamp(@PathVariable String timestampa) throws JSONException, ParseException, SQLException, IOException {
        Timestamp timestamp = (new Timestamp(System.currentTimeMillis()));
        long stamps = timestamp.getTime();
        String stampString = String.valueOf(stamps);
        String stamp = stampString.substring(0, stampString.length()-3);

        long timestamps = Long.valueOf(stamp) - 1209600L;
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        String timestampedLoanAcc = restTemplate.exchange("https://api.demo.irl.musoniservices.com/v1/loans?modifiedSinceTimestamp="+timestamps, HttpMethod.GET, entity, String.class).getBody();

        timestampedLoanAccs.clear();
        for (int i = 0; i<new JSONObject(timestampedLoanAcc).getJSONArray("pageItems").length(); i++) {
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
                phone_number = "0784315526";
            }

            //            Send SMS when days in arrears: 1
            if (status == "true" && Integer.parseInt(days_in_arrears) == 248 && phone_number != "0"){
//                smsService.sendSingle(phone_number, "You have PAR 1 DAY");
            }
            else if (status == "true" && Integer.parseInt(days_in_arrears) == 247 && phone_number != "0"){
//                smsService.sendSingle(phone_number, "You have PAR 14 DAY");
            }
            else if (status == "true" && Integer.parseInt(days_in_arrears) == 121 && phone_number != "0") {
//                smsService.sendSingle(phone_number, "You have PAR 30 DAY");
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

    public String currencyFormatter(String amount){
        Locale usa = new Locale("en", "US");
        NumberFormat currency = NumberFormat.getCurrencyInstance(usa);
        return currency.format(amount);
    }

}
