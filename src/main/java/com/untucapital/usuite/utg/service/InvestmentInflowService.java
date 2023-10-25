package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.InvestmentInfow;
import com.untucapital.usuite.utg.repository.InvestmentInfowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@javax.transaction.Transactional
@Service
public class InvestmentInflowService {
    @Autowired
    InvestmentInfowRepository investmentInfowRepository;

    @Transactional(value = "transactionManager")
    public List<InvestmentInfow> findByLoanId(String loanId){
        return investmentInfowRepository.findInvestmentInfowByLoanId(loanId);
    }

    @Transactional(value = "transactionManager")
    public void delete(String id){
        investmentInfowRepository.deleteById(id);
    }

    @Transactional(value = "transactionManager")
    public void add(InvestmentInfow investmentInfow){
        investmentInfowRepository.save(investmentInfow);
    }
}
