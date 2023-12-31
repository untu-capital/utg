package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.LoanRequest;
import com.untucapital.usuite.utg.model.Meetings;
import com.untucapital.usuite.utg.service.LoanRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loanRequest")
public class LoanRequestController {
    @Autowired
    LoanRequestService loanRequestService;

    private static final Logger log = LoggerFactory.getLogger(LoanRequestController.class);


    //Adding Loand Request details
    @PostMapping("/addLoanRequest")
    public void add(@RequestBody LoanRequest loanRequest) {
        loanRequestService.saveLoanRequest(loanRequest);
    }

    //get LoanRequest by loan Id
    @GetMapping("/getLoanRequestByLoanId/{id}")
    public List<LoanRequest> get(@PathVariable("id") String loanId){
        return loanRequestService.getByLoanId(loanId);
    }

    //Deleteting LoanRequest By Id
    @DeleteMapping("/deleteLoanRequest/{id}")
    public void deleteLoanRequest(@PathVariable String id){
        loanRequestService.deleteLoanRequest(id);
    }
}
