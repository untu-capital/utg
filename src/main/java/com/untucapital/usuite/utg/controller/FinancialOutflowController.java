package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.FinancialInflow;
import com.untucapital.usuite.utg.model.FinancialOutFlow;
import com.untucapital.usuite.utg.service.FinancialOutFlowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("financialOutflow")
public class FinancialOutflowController {
    @Autowired
    FinancialOutFlowService financialOutFlowService;

    private static final Logger log = LoggerFactory.getLogger(FinancialOutflowController.class);

    @GetMapping("/get/{loanId}")
    public List<FinancialOutFlow> getAllByLoanId(@PathVariable("loanId") String loanId) {
        return financialOutFlowService.getOutflow(loanId);
    }

    @PostMapping("/add")
    public void add(@RequestBody FinancialOutFlow financialOutFlow) {
        financialOutFlowService.addOutflow(financialOutFlow);
    }
}
