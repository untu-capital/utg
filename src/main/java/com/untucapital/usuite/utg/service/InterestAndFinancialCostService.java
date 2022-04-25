package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.InterestAndFinancialCost;
import com.untucapital.usuite.utg.repository.InterestAndFinancialCostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class InterestAndFinancialCostService {
    @Autowired
    InterestAndFinancialCostRepository interestAndFinancialCostRepository;

    public List<InterestAndFinancialCost> findByLoanId(String loanId){
        return interestAndFinancialCostRepository.findInterestAndFinancialCostByLoanId(loanId);
    }

    public void save(InterestAndFinancialCost interestAndFinancialCost){
        interestAndFinancialCostRepository.save(interestAndFinancialCost);
    }

    public void delete(String id){
        interestAndFinancialCostRepository.deleteById(id);
    }
}
