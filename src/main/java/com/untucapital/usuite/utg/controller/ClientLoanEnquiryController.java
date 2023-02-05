package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.ClientLoan;
import com.untucapital.usuite.utg.model.ClientLoanEnquiry;
import com.untucapital.usuite.utg.repository.ClientLoanEnquiryRepository;
import com.untucapital.usuite.utg.repository.ClientRepository;
import com.untucapital.usuite.utg.service.ClientLoanApplication;
import com.untucapital.usuite.utg.service.ClientLoanEnquiryService;
import com.untucapital.usuite.utg.utils.EmailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "credit_application_enquiry")
public class ClientLoanEnquiryController {


    @Autowired
    ClientLoanEnquiryRepository clientLoanEnquiryRepository;

    @Autowired
    ClientLoanEnquiryService clientLoanEnquiryService;

    private static final Logger log = LoggerFactory.getLogger(ClientLoanEnquiryController.class);

    //build save loan REST API
    @PostMapping
    public ResponseEntity<ClientLoanEnquiry> saveClientLoanEnquiry(@RequestBody ClientLoanEnquiry clientLoanEnquiry) {
        log.info(String.valueOf(clientLoanEnquiry));
        return new ResponseEntity<ClientLoanEnquiry>(clientLoanEnquiryService.saveClientLoanEnquiry(clientLoanEnquiry), HttpStatus.CREATED);
    }

    //build get all loan applications REST API
    @GetMapping
    public List<ClientLoanEnquiry> getAllClientLoanEnquiries() {
        return clientLoanEnquiryService.getAllClientLoanEnquiries();

    }

    //build get clientLoan by ID REST API
    @GetMapping("{id}")
    public ResponseEntity<ClientLoanEnquiry> getClientLoanEnquiryById(@PathVariable("id") String id) {
        return new ResponseEntity<ClientLoanEnquiry>(clientLoanEnquiryRepository.findClientLoanEnquiryById(id), HttpStatus.OK);
    }

    //build get clientLoan by ID REST API
    @GetMapping("userid{userId}")
    public ResponseEntity<List<ClientLoanEnquiry>> getClientLoanEnquiryByUserId(@PathVariable("userId") String userId) {
        return new ResponseEntity<List<ClientLoanEnquiry>>(clientLoanEnquiryRepository.findClientLoanEnquiryByUserId(userId), HttpStatus.OK);
    }


}




