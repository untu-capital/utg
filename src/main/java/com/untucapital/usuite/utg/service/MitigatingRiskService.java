package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.MitigatingRisk;
import com.untucapital.usuite.utg.repository.MitigatingRiskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@javax.transaction.Transactional
public class MitigatingRiskService {
    @Autowired
    MitigatingRiskRepository mitigatingRiskRepository;

    @Transactional(value = "transactionManager")
    public void saveMitigatingRisk(MitigatingRisk mitigatingRisk){
        mitigatingRiskRepository.save(mitigatingRisk);
    }

    @Transactional(value = "transactionManager")
    public List<MitigatingRisk> getMitigatingRiskByLoanId(String loanId){
        return mitigatingRiskRepository.findByLoanId(loanId);
    }

    @Transactional(value = "transactionManager")
    public void deleteMitigatingRisk(String id){
        mitigatingRiskRepository.deleteById(id);
    }
}

