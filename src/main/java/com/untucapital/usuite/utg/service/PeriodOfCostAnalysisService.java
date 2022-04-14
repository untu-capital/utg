package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.OperationalCost;
import com.untucapital.usuite.utg.model.PeriodOfCostAnalysis;
import com.untucapital.usuite.utg.repository.OperationalCostRepository;
import com.untucapital.usuite.utg.repository.PeriodOfCostAnalysisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class PeriodOfCostAnalysisService {
    @Autowired
    private PeriodOfCostAnalysisRepository periodOfCostAnalysisRepository;

    public List<PeriodOfCostAnalysis> getPeriod(String loanId) {
        return periodOfCostAnalysisRepository.findByLoanId(loanId);
    }

    public void  savePeriod(PeriodOfCostAnalysis periodOfCostAnalysis) {
        if(periodOfCostAnalysisRepository.existsById(periodOfCostAnalysis.getId())){

        }else{
            periodOfCostAnalysisRepository.save(periodOfCostAnalysis);
        }
    }

    public List<PeriodOfCostAnalysis> getAll() {
        return periodOfCostAnalysisRepository.findAll();
    }
}
