package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.InterestAndFinancialCost;
import com.untucapital.usuite.utg.repository.InterestAndFinancialCostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@javax.transaction.Transactional
@Service
public class InterestAndFinancialCostService {
    @Autowired
    InterestAndFinancialCostRepository interestAndFinancialCostRepository;

    @Transactional(value = "transactionManager")
    public List<InterestAndFinancialCost> findByLoanId(String loanId){
        return interestAndFinancialCostRepository.findInterestAndFinancialCostByLoanId(loanId);
    }

    @Transactional(value = "transactionManager")
    public void save(InterestAndFinancialCost interestAndFinancialCost){
        interestAndFinancialCostRepository.save(interestAndFinancialCost);
    }

    @Transactional(value = "transactionManager")
    public void delete(String id){
        interestAndFinancialCostRepository.deleteById(id);
    }
}
