package com.untucapital.usuite.utg.controller;


import com.untucapital.usuite.utg.model.Business;
import com.untucapital.usuite.utg.model.DirectCost;
import com.untucapital.usuite.utg.service.DirectCostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/direct_cost")
public class DirectCostController {

    @Autowired
    DirectCostService directCostService;

    private static final Logger log = LoggerFactory.getLogger(DirectCostController.class);



    @GetMapping("")
    public List<DirectCost> getAllDirectCosts() {
        return directCostService.getAllDirectCosts();
    }

    @GetMapping("/get_costs/{loanId}")
    public List<DirectCost> getAllDirectCostsByLoanId(@PathVariable("loanId") String loanId) {
        return directCostService.getDirectCostsByLoanId(loanId);
    }

    @PostMapping("/addDirectCost")
    public void add(@RequestBody DirectCost directCost) {
        directCostService.saveDirectCost(directCost);
    }

    @DeleteMapping("/deleteDirectCost/{loanId}")
    public void delete(@PathVariable String id) {

        directCostService.deleteDirectCost(id);
    }

}
