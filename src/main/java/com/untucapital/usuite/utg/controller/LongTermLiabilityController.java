package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.LongTermLiability;
import com.untucapital.usuite.utg.model.ShortTermLiability;
import com.untucapital.usuite.utg.service.LongTermLiabilityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("longTermLiability")
public class LongTermLiabilityController {
    @Autowired
    LongTermLiabilityService longTermLiabilityService;

    private static final Logger log = LoggerFactory.getLogger(LongTermLiabilityController.class);

    @GetMapping("/get/{loanId}")
    public List<LongTermLiability> getAllByLoanId(@PathVariable("loanId") String loanId) {
        return longTermLiabilityService.getLiability(loanId);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable String id) {
        longTermLiabilityService.deleteLiability(id);
    }
    @PostMapping("/add")
    public void add(@RequestBody LongTermLiability longTermLiability) {
        longTermLiabilityService.saveLiability(longTermLiability);
    }

}
