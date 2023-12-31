package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.BankTo;
import com.untucapital.usuite.utg.service.BankToService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bankTo")
public class BankToController {

    @Autowired
    private final BankToService bankToService;

    public BankToController(BankToService bankToService) {
        this.bankToService = bankToService;
    }

    @PostMapping("/addBankTo")
    public void add(@RequestBody BankTo bankTo) {
        bankToService.saveBankTo(bankTo);
    }

    @GetMapping("getBankTo/{loanId}/{bank}")
    public List<BankTo> getBankTo(@PathVariable("loanId") String id, @PathVariable("bank") String bank){
        return bankToService.listBankToByLoanId(id, bank);
    }
    @DeleteMapping("/deleteBankTo/{id}")
    public void deleteById(@PathVariable String id){
        bankToService.deleteById(id);
    }

}
