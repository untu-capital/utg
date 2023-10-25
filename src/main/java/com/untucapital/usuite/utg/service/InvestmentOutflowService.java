package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.InvestmentOutflow;
import com.untucapital.usuite.utg.repository.InvestmentOutflowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@javax.transaction.Transactional
public class InvestmentOutflowService {

    @Autowired
    InvestmentOutflowRepository investmentOutflowRepository;

    @Transactional(value = "transactionManager")
    public void add(InvestmentOutflow investmentOutflow){
        investmentOutflowRepository.save(investmentOutflow);
    }

    @Transactional(value = "transactionManager")
    public void delete(String id){
        investmentOutflowRepository.deleteById(id);
    }

    @Transactional(value = "transactionManager")
    public List<InvestmentOutflow> get(String loanId){
        return investmentOutflowRepository.findInvestmentOutflowByLoanId(loanId);
    }
}
