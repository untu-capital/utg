
package com.untucapital.usuite.utg.controller;
import com.untucapital.usuite.utg.UntuTransactionGatewayApplication;
import com.untucapital.usuite.utg.model.MusoniPastel;
import com.untucapital.usuite.utg.service.MusoniService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.persistence.QueryHint;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import static org.hibernate.annotations.QueryHints.READ_ONLY;
import static org.hibernate.jpa.QueryHints.HINT_CACHEABLE;
import static org.hibernate.jpa.QueryHints.HINT_FETCH_SIZE;

@AllArgsConstructor
@RestController
@RequestMapping(value ="musoni", produces="application/json")
public class MusoniController {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    MusoniService musoniService;

    HttpHeaders headers;

    private static final Logger log = LoggerFactory.getLogger(MusoniPastelController.class);

    //    Get All Clients
    @GetMapping("clients")
    public String getAllClients() {

        HttpEntity<String> entity = new HttpEntity<String>(headers);
        return restTemplate.exchange("https://api.demo.irl.musoniservices.com/v1/clients", HttpMethod.GET, entity, String.class).getBody();
    }

    //Get Client By Id
    @GetMapping("clients/{clientId}")
    public String getClientById(@PathVariable Long clientId) {
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        return restTemplate.exchange("https://api.demo.irl.musoniservices.com/v1/clients/"+clientId, HttpMethod.GET, entity, String.class).getBody();
    }

    //Get Client By Status
    @GetMapping("clients/status/{status}")
    public String getClientsByStatus(@PathVariable String status) {
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        return restTemplate.exchange("https://api.demo.irl.musoniservices.com/v1/clients/"+status, HttpMethod.GET, entity, String.class).getBody();
    }

    //Get Loan By Id
    @GetMapping("loans/{loanId}")
    public String getLoanById(@PathVariable Long loanId) {
        HttpEntity<String> entity = new HttpEntity<String>(headers);
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
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        return restTemplate.exchange("https://api.demo.irl.musoniservices.com/v1/loans?disbursementFromDate=" + dates[0] + "&disbursementToDate=" + dates[1] + "&limit=2000&orderBy=id&sortOrder=DESC", HttpMethod.GET, entity, String.class).getBody();
    }

    //    Collect Transaction from Musoni
    @GetMapping("/getTransations/loanid/{loanId}/transactionId/{transactionId}")
    public String getMusoniPastelTrans(@PathVariable("loanId") String loanId, @PathVariable("transactionId") String transactionId) {

        HttpEntity<String> entity = new HttpEntity<String>(headers);
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
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        return restTemplate.exchange("https://api.demo.irl.musoniservices.com/v1/clients/"+clientId+"/accounts", HttpMethod.GET, entity, String.class).getBody();
    }

    //Get Loan Repayment Schedule By Id
    @GetMapping("loansRepaymentSchedule/{loanId}")
    public String getLoanLoanRepaymentScheduleById(@PathVariable Long loanId) {
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        return restTemplate.exchange("https://api.demo.irl.musoniservices.com/v1/loans/"+loanId+"?associations=repaymentSchedule", HttpMethod.GET, entity, String.class).getBody();
    }

    //Get Clients List By Search Filter
    @GetMapping("clientsSearchFilter/{searchFilter}")
    public String getClientListBySearchFilter(@PathVariable String searchFilter) {
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        return restTemplate.exchange("https://api.demo.irl.musoniservices.com/v1/clients?search="+searchFilter, HttpMethod.GET, entity, String.class).getBody();
    }

    //Get ClientID by PhoneNumber
    @PostMapping("datafilters")
    public String getClientIDByDataFilter(@RequestBody Map<String,Object> body ) {
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        return restTemplate.exchange("https://api.demo.irl.musoniservices.com/v1/datafilters/clients/queries/run-filter", HttpMethod.GET, entity, String.class).getBody();
    }

}
