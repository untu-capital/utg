package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.OperationalCost;
import com.untucapital.usuite.utg.service.OperationalCostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operational_cost")
public class OperationalCostController {

    @Autowired
    OperationalCostService operationalCostService;

    private static final Logger log = LoggerFactory.getLogger(OperationalCostController.class);

    @GetMapping("")
    public List<OperationalCost> getAllDirectCosts() {
        return operationalCostService.getAllDirectCosts();
    }

    @GetMapping("/get_costs/{loanId}")
    public List<OperationalCost> getAllDirectCostsByLoanId(@PathVariable("loanId") String loanId) {
        return operationalCostService.getCostsByLoanId(loanId);
    }

    @PostMapping("/addOperationalCost")
    public void add(@RequestBody OperationalCost operationalCost) {
        operationalCostService.saveDirectCost(operationalCost);
    }

    @DeleteMapping("/deleteDirectCost/{loanId}")
    public void delete(@PathVariable String id) {
        operationalCostService.deleteOperationalCost(id);
    }
}
