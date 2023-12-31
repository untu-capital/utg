package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.CreditHistory;
import com.untucapital.usuite.utg.model.MainCompetitor;
import com.untucapital.usuite.utg.service.CreditHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("credit_history")
public class CreditHistoryController {
    @Autowired
    CreditHistoryService creditHistoryService;

    private static final Logger log = LoggerFactory.getLogger(MainCompetitorController.class);

    @GetMapping("/get/{loanId}")
    public List<CreditHistory> getByLoanId(@PathVariable("loanId") String loanId) {
        return creditHistoryService.getCreditHistoryByLoanId(loanId);
    }

    @PostMapping("/save")
    public void add(@RequestBody CreditHistory creditHistory) {
        creditHistoryService.saveCreditHistory(creditHistory);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        creditHistoryService.deleteCreditHistory(id);
    }


}
