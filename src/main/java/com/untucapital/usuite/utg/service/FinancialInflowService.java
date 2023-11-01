package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.FinancialInflow;
import com.untucapital.usuite.utg.repository.FinancialInflowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@javax.transaction.Transactional
@Service
public class FinancialInflowService {
    @Autowired
    FinancialInflowRepository financialInflowRepository;

    @Transactional(value = "transactionManager")
    public void addInflow(FinancialInflow financialInflow){
        financialInflowRepository.save(financialInflow);
    }

    @Transactional(value = "transactionManager")
    public List<FinancialInflow> getInflow(String loanId){
        return financialInflowRepository.findFinancialInflowByLoanId(loanId);
    }

    @Transactional(value = "transactionManager")
    public void deleteInflow(String id){
        financialInflowRepository.deleteById(id);
    }


}
