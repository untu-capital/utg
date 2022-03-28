package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.CurrentAsset;
import com.untucapital.usuite.utg.model.FinancialInflow;
import com.untucapital.usuite.utg.service.CurrentAssetsService;
import com.untucapital.usuite.utg.service.FinancialInflowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("financialInflow")
public class FinancialInflowController {

    @Autowired
    FinancialInflowService financialInflowService;

    private static final Logger log = LoggerFactory.getLogger(FinancialInflowController.class);

    @GetMapping("/get/{loanId}")
    public List<FinancialInflow> getAllByLoanId(@PathVariable("loanId") String loanId) {
        return financialInflowService.getInflow(loanId);
    }

    @PostMapping("/add")
    public void add(@RequestBody FinancialInflow financialInflow) {
        financialInflowService.addInflow(financialInflow);
    }

}
