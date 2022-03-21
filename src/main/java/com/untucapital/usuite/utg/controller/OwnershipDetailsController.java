package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.OwnershipDetails;
import com.untucapital.usuite.utg.service.OwnershipDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ownership_details")
public class OwnershipDetailsController {
    @Autowired
    OwnershipDetailsService ownershipDetailsService;

    private static final Logger log = LoggerFactory.getLogger(OwnershipDetailsController.class);

    @GetMapping("/ownership_details/{loanId}")
    public List<OwnershipDetails> getByLoanId(@PathVariable("loanId") String loanId) {
        return ownershipDetailsService.getOwnershipDetailsByLoanId(loanId);
    }

    @PostMapping("/ownership_details")
    public void add(@RequestBody OwnershipDetails ownershipDetails) {
        ownershipDetailsService.saveOwenershipDetails(ownershipDetails);
    }

    @DeleteMapping("/delete_ownership_details/{id}")
    public void delete(@PathVariable String id) {
        ownershipDetailsService.deleteOwnershipDetails(id);
    }
}
