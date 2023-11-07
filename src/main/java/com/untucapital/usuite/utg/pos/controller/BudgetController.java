package com.untucapital.usuite.utg.pos.controller;

import com.untucapital.usuite.utg.dto.request.BudgetRequestDTO;
import com.untucapital.usuite.utg.dto.response.BudgetResponseDTO;
import com.untucapital.usuite.utg.pos.service.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tjchidanika
 * @created 5/9/2023
 */


@RestController
@RequestMapping(value = "pos/budget")
@RequiredArgsConstructor
public class BudgetController {
    private final BudgetService budgetService;

    //Save Budget
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public BudgetResponseDTO saveBudget(@RequestBody BudgetRequestDTO budget) {
        return budgetService.createBudget(budget);
    }

    //Get Budget By Id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BudgetResponseDTO getBudgetById(@PathVariable Integer id) {
        return budgetService.getBudgetById(id);
    }

    //Get all Budgets
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<BudgetResponseDTO> getAllBudgets() {
        return budgetService.getAllBudgets();
    }

    //Update Budget
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public BudgetResponseDTO updateBudget(@RequestBody BudgetRequestDTO budget) {
        return budgetService.updateBudget(budget);
    }

    //Delete Budget
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BudgetResponseDTO deleteBudget(@PathVariable Integer id) {
        return budgetService.deleteBudget(id);
    }
}

