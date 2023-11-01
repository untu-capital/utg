package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.LoansPipeline;
import com.untucapital.usuite.utg.repository.LoansPipelineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "credit_application_pipeline")
public class LoansPipelineController {

    private final LoansPipelineRepository loansPipelineRepository;

    @Autowired
    public LoansPipelineController(LoansPipelineRepository loansPipelineRepository) {
        this.loansPipelineRepository = loansPipelineRepository;
    }

    @PostMapping("/loans")
    public ResponseEntity<LoansPipeline> createLoan(@RequestBody LoansPipeline loansPipeline) {
        LoansPipeline savedLoan = loansPipelineRepository.save(loansPipeline);
        return new ResponseEntity<>(savedLoan, HttpStatus.CREATED);
    }

    @GetMapping("/loans")
    public ResponseEntity<Iterable<LoansPipeline>> getAllLoans() {
        Iterable<LoansPipeline> loans = loansPipelineRepository.findAll();
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }

    @GetMapping("/loans/count/{loanOfficer}")
    public ResponseEntity<Integer> getCountByLoanOfficer(@PathVariable("loanOfficer") String loanOfficer) {
        int count = loansPipelineRepository.countByLoanOfficerAndLoanStatus(loanOfficer, "Disbursed");
        return new ResponseEntity<>(count, HttpStatus.OK);
    }


}
