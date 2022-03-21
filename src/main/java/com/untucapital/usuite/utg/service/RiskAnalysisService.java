package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.LongTermCreditHistory;
import com.untucapital.usuite.utg.model.RiskAnalysis;
import com.untucapital.usuite.utg.repository.RiskAnalysisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class RiskAnalysisService {
    @Autowired
    RiskAnalysisRepository riskAnalysisRepository;

    public void saveRiskAnalysis(RiskAnalysis riskAnalysis){
        riskAnalysisRepository.save(riskAnalysis);
    }

    public List<RiskAnalysis> getRiskAnalysisByLoanId(String loanId){
        return riskAnalysisRepository.findByLoanId(loanId);
    }

    public void deleteRiskAnalysis(String id){
        riskAnalysisRepository.deleteById(id);
    }
}


