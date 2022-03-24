package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.model.RepaymentHistory;
import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.RepaymentHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "repaymentHistory")
public class RepaymentHistoryController {

    @Autowired
    private final RepaymentHistoryService repaymentHistoryService;

    public RepaymentHistoryController(RepaymentHistoryService repaymentHistoryService) {
        this.repaymentHistoryService = repaymentHistoryService;
    }
    //Add
    @PostMapping("/save")
    public void save(@RequestBody RepaymentHistory repaymentHistory){
        repaymentHistoryService.save(repaymentHistory);
    }
    //Delete By Id
    @DeleteMapping("delete/{repaymentHistoryId}")
    public void deleteById(@PathVariable("repaymentHistoryId") String id){
        repaymentHistoryService.deleteBybId(id);
    }
    //Find All BY Loan Id
    @GetMapping("get/{loanId}")
    public List<RepaymentHistory> findAllByLoanId(@PathVariable("loanId") String id){
        return repaymentHistoryService.findAllByLoanId(id);
    }

}
