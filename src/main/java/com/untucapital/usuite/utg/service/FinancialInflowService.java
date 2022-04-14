package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.FinancialInflow;
import com.untucapital.usuite.utg.model.FixedAsset;
import com.untucapital.usuite.utg.repository.FinancialInflowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class FinancialInflowService {
    @Autowired
    FinancialInflowRepository financialInflowRepository;

    public void addInflow(FinancialInflow financialInflow){
        financialInflowRepository.save(financialInflow);
    }

    public List<FinancialInflow> getInflow(String loanId){
        return financialInflowRepository.findFinancialInflowByLoanId(loanId);
    }

    public void deleteInflow(String id){
        financialInflowRepository.deleteById(id);
    }


}
