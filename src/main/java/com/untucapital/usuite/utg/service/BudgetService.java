package com.untucapital.usuite.utg.service;

//import com.untucapital.usuite.utg.model.Budget;
//import com.untucapital.usuite.utg.repository.BudgetRepository;
import com.untucapital.usuite.utg.model.Stock;
import com.untucapital.usuite.utg.repository.cms.BudgetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author tjchidanika
 * @created 5/9/2023
 */

@Service
@RequiredArgsConstructor
public class BudgetService {
    public final BudgetRepository budgetRepository;
    //1. create budget
    @Transactional(value = "transactionManager")
    public Stock.Budget createBudget(Stock.Budget budget) {
        return budgetRepository.save(budget);
    }
    //2. get budget by id
    @Transactional(value = "transactionManager")
    public Stock.Budget getBudgetById(Integer id) {
        return budgetRepository.findById(id).orElse(null);
    }
    //3. get all budgets
    @Transactional(value = "transactionManager")
    public List<Stock.Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }
    //4. update budget
    @Transactional(value = "transactionManager")
    public Stock.Budget updateBudget(Stock.Budget budget) {
        Stock.Budget existingBudget = budgetRepository.findById(budget.getId()).orElse(null);
        assert existingBudget != null;
        existingBudget.setCategory(budget.getCategory());
        existingBudget.setYear(budget.getYear());
        existingBudget.setJanuary(budget.getJanuary());
        existingBudget.setFebruary(budget.getFebruary());
        existingBudget.setMarch(budget.getMarch());
        existingBudget.setApril(budget.getApril());
        existingBudget.setMay(budget.getMay());
        existingBudget.setJune(budget.getJune());
        existingBudget.setJuly(budget.getJuly());
        existingBudget.setAugust(budget.getAugust());
        existingBudget.setSeptember(budget.getSeptember());
        existingBudget.setOctober(budget.getOctober());
        existingBudget.setNovember(budget.getNovember());
        existingBudget.setDecember(budget.getDecember());
        return budgetRepository.save(existingBudget);
    }
    //5. delete budget
    @Transactional(value = "transactionManager")
    public Stock.Budget deleteBudget(Integer id) {
        Stock.Budget budget = budgetRepository.findById(id).orElse(null);
        budgetRepository.deleteById(id);
        return budget;
    }
}
