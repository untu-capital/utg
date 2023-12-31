package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.model.OperationalExpenses;
import com.untucapital.usuite.utg.micro.qualitativeAssesment.repository.OperationalExpensesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationalExpensesService {
    @Autowired
    private final OperationalExpensesRepository operationalExpensesRepository;

    public OperationalExpensesService(OperationalExpensesRepository operationalExpensesRepository) {
        this.operationalExpensesRepository = operationalExpensesRepository;
    }
    //Add
    public void save(OperationalExpenses operationalExpenses){
        operationalExpensesRepository.save(operationalExpenses);
    }
    //Delete by id
    public void deleteById(String id){
        operationalExpensesRepository.deleteById(id);
    }
    //Find All By Loan id
    public List<OperationalExpenses> findAllByLoanId(String id){
        return operationalExpensesRepository.findAllByLoanId(id);
    }
}
