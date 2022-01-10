package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.ClientLoan;
import com.untucapital.usuite.utg.service.ClientLoanApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "credit_application")
public class ClientLoanController {

    private static final Logger log = LoggerFactory.getLogger(ClientLoanController.class);

    private ClientLoanApplication clientLoanApplication;

    public ClientLoanController(ClientLoanApplication clientLoanApplication) {
        this.clientLoanApplication = clientLoanApplication;
    }

    //build save loan REST API
    @PostMapping
    public ResponseEntity<ClientLoan> saveClientLoan(@RequestBody ClientLoan clientLoan) {
        log.info(String.valueOf(clientLoan));
        return new ResponseEntity<ClientLoan>(clientLoanApplication.saveClientLoan(clientLoan), HttpStatus.CREATED);
    }

    //build get all loan applications REST API
    @GetMapping
    public List<ClientLoan> getAllClientLoanApplication() {
        return clientLoanApplication.getAllClientLoanApplication();

    }

    //build get clientLoan by ID REST API
    @GetMapping("{id}")
    public ResponseEntity<ClientLoan> getClientLoanApplicationById(@PathVariable("id") String clientloanID) {
        return new ResponseEntity<ClientLoan>(clientLoanApplication.getClientLoanApplicationById(clientloanID), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsByUserId(@PathVariable("userId") String userId) {

        List<ClientLoan> userClientLoans = clientLoanApplication.getClientLoanApplicationsByUserId(userId);
        return ResponseEntity.ok(userClientLoans);
    }

    //build update clientLoan REST API
    @PutMapping("/update/{id}")
    public ResponseEntity<ClientLoan> updateClient(@PathVariable("id") String id, @RequestBody ClientLoan clientLoan) {
        return new ResponseEntity<ClientLoan>(clientLoanApplication.updateClientLoan(clientLoan, id), HttpStatus.OK);
    }

    //build delete client loan application REST api
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteClientLoan(@PathVariable("id") String id) {
        //delete client loan from DB
        clientLoanApplication.deleteClientLoan(id);
        return new ResponseEntity<String>("Application successfully deleted.", HttpStatus.OK);

    }
}




