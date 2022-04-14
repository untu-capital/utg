package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.LongTermCreditHistory;
import com.untucapital.usuite.utg.model.MitigatingRisk;
import com.untucapital.usuite.utg.repository.MitigatingRiskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class MitigatingRiskService {
    @Autowired
    MitigatingRiskRepository mitigatingRiskRepository;

    public void saveMitigatingRisk(MitigatingRisk mitigatingRisk){
        mitigatingRiskRepository.save(mitigatingRisk);
    }

    public List<MitigatingRisk> getMitigatingRiskByLoanId(String loanId){
        return mitigatingRiskRepository.findByLoanId(loanId);
    }

    public void deleteMitigatingRisk(String id){
        mitigatingRiskRepository.deleteById(id);
    }
}

