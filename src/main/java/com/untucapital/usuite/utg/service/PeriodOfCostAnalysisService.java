package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.OperationalCost;
import com.untucapital.usuite.utg.model.PeriodOfCostAnalysis;
import com.untucapital.usuite.utg.repository.OperationalCostRepository;
import com.untucapital.usuite.utg.repository.PeriodOfCostAnalysisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@javax.transaction.Transactional
@Service
public class PeriodOfCostAnalysisService {
    @Autowired
    private PeriodOfCostAnalysisRepository periodOfCostAnalysisRepository;

    @Transactional(value = "transactionManager")
    public List<PeriodOfCostAnalysis> getPeriod(String loanId) {
        return periodOfCostAnalysisRepository.findByLoanId(loanId);
    }

    @Transactional(value = "transactionManager")
    public void  savePeriod(PeriodOfCostAnalysis periodOfCostAnalysis) {
        if(periodOfCostAnalysisRepository.existsById(periodOfCostAnalysis.getId())){

        }else{
            periodOfCostAnalysisRepository.save(periodOfCostAnalysis);
        }
    }

    @Transactional(value = "transactionManager")
    public List<PeriodOfCostAnalysis> getAll() {
        return periodOfCostAnalysisRepository.findAll();
    }
}
