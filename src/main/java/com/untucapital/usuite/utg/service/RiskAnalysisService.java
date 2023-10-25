package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.LongTermCreditHistory;
import com.untucapital.usuite.utg.model.RiskAnalysis;
import com.untucapital.usuite.utg.repository.RiskAnalysisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@javax.transaction.Transactional
@Service
public class RiskAnalysisService {
    @Autowired
    RiskAnalysisRepository riskAnalysisRepository;

    @Transactional(value = "transactionManager")
    public void saveRiskAnalysis(RiskAnalysis riskAnalysis){
        riskAnalysisRepository.save(riskAnalysis);
    }

    @Transactional(value = "transactionManager")
    public List<RiskAnalysis> getRiskAnalysisByLoanId(String loanId){
        return riskAnalysisRepository.findByLoanId(loanId);
    }

    @Transactional(value = "transactionManager")
    public void deleteRiskAnalysis(String id){
        riskAnalysisRepository.deleteById(id);
    }
}


