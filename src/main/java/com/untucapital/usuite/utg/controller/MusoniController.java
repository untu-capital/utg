
package com.untucapital.usuite.utg.controller;
import com.untucapital.usuite.utg.model.ClientLoan;
import com.untucapital.usuite.utg.service.MusoniService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value ="musoni", produces="application/json")
public class MusoniController {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    MusoniService musoniService;

    HttpHeaders headers;

//    @Value("${untu.musoni-path.link}")
//    private String musoniUrl;

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

}
