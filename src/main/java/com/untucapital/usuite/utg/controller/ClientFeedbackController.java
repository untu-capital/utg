package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.ClientFeedback;
import com.untucapital.usuite.utg.model.ClientLoan;
import com.untucapital.usuite.utg.model.ClientLoanEnquiry;
import com.untucapital.usuite.utg.repository.ClientFeedbackRepository;
import com.untucapital.usuite.utg.repository.ClientLoanEnquiryRepository;
import com.untucapital.usuite.utg.repository.ClientRepository;
import com.untucapital.usuite.utg.service.ClientFeedbackService;
import com.untucapital.usuite.utg.service.ClientLoanApplication;
import com.untucapital.usuite.utg.service.ClientLoanEnquiryService;
import com.untucapital.usuite.utg.utils.EmailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "credit_application_feedback")
public class ClientFeedbackController {


    @Autowired
    ClientFeedbackRepository clientFeedbackRepository;

    @Autowired
    ClientFeedbackService clientFeedbackService;

    private static final Logger log = LoggerFactory.getLogger(ClientFeedbackController.class);

    //build save loan REST API
    @PostMapping
    public ResponseEntity<ClientFeedback> saveClientFeedback(@RequestBody ClientFeedback clientFeedback) {
        log.info(String.valueOf(clientFeedback));
        return new ResponseEntity<ClientFeedback>(clientFeedbackService.saveClientFeedback(clientFeedback), HttpStatus.CREATED);
    }

    //build get all loan applications REST API
    @GetMapping
    public List<ClientFeedback> getAllClientLoanEnquiries() {
        return clientFeedbackService.getAllClientFeedback();

    }

    //build get clientLoan by ID REST API
    @GetMapping("{id}")
    public ResponseEntity<ClientFeedback> getClientFeedbackById(@PathVariable("id") String id) {
        return new ResponseEntity<ClientFeedback>((MultiValueMap<String, String>) clientFeedbackRepository.findClientFeedbackById(id), HttpStatus.OK);
    }

    //build get clientLoan by ID REST API
    @GetMapping("/userid/{userId}")
    public ResponseEntity<List<ClientFeedback>> getClientFeedbackByUserId(@PathVariable("userId") String userId) {
        return new ResponseEntity<List<ClientFeedback>>(clientFeedbackRepository.findClientFeedbackByUserId(userId), HttpStatus.OK);
    }

}




