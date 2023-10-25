package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.FinancialOutFlow;
import com.untucapital.usuite.utg.repository.FinancialOutFlowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@javax.transaction.Transactional
@Service
public class FinancialOutFlowService {
    @Autowired
    FinancialOutFlowRepository financialOutFlowRepository;

    @Transactional(value = "transactionManager")
    public void addOutflow(FinancialOutFlow financialOutFlow){
        financialOutFlowRepository.save(financialOutFlow);
    }

    @Transactional(value = "transactionManager")
    public List<FinancialOutFlow> getOutflow(String loanId){
        return financialOutFlowRepository.findFinancialOutFlowByLoanId(loanId);
    }

    @Transactional(value = "transactionManager")
    public void deleteOutflow(String id){
        financialOutFlowRepository.deleteById(id);
    }
}
