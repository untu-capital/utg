package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.FinancialInflow;
import com.untucapital.usuite.utg.model.FinancialOutFlow;
import com.untucapital.usuite.utg.repository.FinancialInflowRepository;
import com.untucapital.usuite.utg.repository.FinancialOutFlowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class FinancialOutFlowService {
    @Autowired
    FinancialOutFlowRepository financialOutFlowRepository;

    public void addOutflow(FinancialOutFlow financialOutFlow){
        financialOutFlowRepository.save(financialOutFlow);
    }

    public List<FinancialOutFlow> getOutflow(String loanId){
        return financialOutFlowRepository.findFinancialOutFlowByLoanId(loanId);
    }

    public void deleteOutflow(String id){
        financialOutFlowRepository.deleteById(id);
    }
}
