package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.DirectCost;
import com.untucapital.usuite.utg.model.SourceOfFunds;
import com.untucapital.usuite.utg.service.SourceOfFundsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/source_of_funds")
public class SourceOfFundsController {

    @Autowired
    SourceOfFundsService sourceOfFundsService;

    private static final Logger log = LoggerFactory.getLogger(SourceOfFundsController.class);

    @GetMapping("/get_costs/{loanId}")
    public List<SourceOfFunds> getByLoanId(@PathVariable("loanId") String loanId) {
        return sourceOfFundsService.getSourceOfFundsByLoanId(loanId);
    }

    @PostMapping("/addDirectCost")
    public void add(@RequestBody SourceOfFunds sourceOfFunds) {
        sourceOfFundsService.saveSourceOfFunds(sourceOfFunds);
    }

    @DeleteMapping("/deleteSourceOfFunds/{loanId}")
    public void delete(@PathVariable String id){
        sourceOfFundsService.deleteSourceOfFunds(id);
    }

}
