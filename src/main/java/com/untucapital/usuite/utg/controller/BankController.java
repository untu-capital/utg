package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.Bank;
import com.untucapital.usuite.utg.model.LoanRequest;
import com.untucapital.usuite.utg.repository.BankRepository;
import com.untucapital.usuite.utg.service.BankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class BankController {
    @Autowired
    BankService bankService;

    private static final Logger log = LoggerFactory.getLogger(BankController.class);

    //Adding Bank details
    @PostMapping("/addBank")
    public void add(@RequestBody Bank bank) {
        bankService.saveBank(bank);
    }

    //get Bank by loan Id
    @GetMapping("/getByLoanId/{id}")
    public List<Bank> get(@PathVariable("id") String loanId){
        return bankService.getByLoanId(loanId);
    }

}
