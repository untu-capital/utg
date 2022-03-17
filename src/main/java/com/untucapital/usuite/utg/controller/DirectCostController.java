package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.DirectCost;
import com.untucapital.usuite.utg.service.DirectCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("direct_costs")
public class DirectCostController{

    @Autowired
    private final DirectCostService directCostService;

    public DirectCostController(DirectCostService directCostService) {
        this.directCostService = directCostService;
    }

    //Save Direct Cost
    @PostMapping("save")
    public void addDirectCost(@RequestBody DirectCost directCost){
        directCostService.addDirectCost(directCost);
    }

    //Find all Direct Costs and sort by day created
    @GetMapping("get/{LoanId}")
    public List<DirectCost> getDirectCost(@PathVariable("LoanId") String id){
        return directCostService.allDirectCost(id);
    }

    //Delete Direct Cost by Id
    @DeleteMapping("delete/{id}")
    public void deleteDirectCost(@PathVariable  String id){
        directCostService.deleteDirectCost(id);
    }


}
