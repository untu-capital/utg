package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.FixedAsset;
import com.untucapital.usuite.utg.model.ShortTermLiability;
import com.untucapital.usuite.utg.service.ShortTermLiabilityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shortTermLiability")
public class ShortTermLiabilityController {
    @Autowired
    ShortTermLiabilityService shortTermLiabilityService;

    private static final Logger log = LoggerFactory.getLogger(ShortTermLiabilityController.class);

    @GetMapping("/get/{loanId}")
    public List<ShortTermLiability> getAllByLoanId(@PathVariable("loanId") String loanId) {
        return shortTermLiabilityService.getLiability(loanId);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable String id){
        shortTermLiabilityService.deleteLiablity(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody ShortTermLiability shortTermLiability) {
        shortTermLiabilityService.saveLiability(shortTermLiability);
    }


}
