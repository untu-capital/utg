package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.model.LoanRequestMicro;
import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.LoanRequestMicroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class LoanRequestMicroController {
    @Autowired
    private final LoanRequestMicroService loanRequestMicroService;

    public LoanRequestMicroController(LoanRequestMicroService loanRequestMicroService) {
        this.loanRequestMicroService = loanRequestMicroService;
    }
    //add
    @PostMapping("/save")
    public void save(@RequestBody LoanRequestMicro loanRequestMicro){
        loanRequestMicroService.save(loanRequestMicro);
    }
    //delete by id
    @DeleteMapping("/delete/{loanRequestId}")
    public void deleteById(@PathVariable("loanRequestId") String id){
        loanRequestMicroService.deleteById(id);
    }
    //get all by loan id
    @GetMapping("/get/{loanId}")
    public List<LoanRequestMicro> findAllByLoanId(@PathVariable("loanId") String id){
        return loanRequestMicroService.findAllByLoanId(id);
    }
}
