package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.LongTermLiability;
import com.untucapital.usuite.utg.repository.LongTermLiabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LongTermLiabilityService {
    @Autowired
    private final LongTermLiabilityRepository longTermLiabilityRepository;

    public LongTermLiabilityService(LongTermLiabilityRepository longTermLiabilityRepository) {
        this.longTermLiabilityRepository = longTermLiabilityRepository;
    }

    public void saveLiability(LongTermLiability longTermLiability){
        longTermLiabilityRepository.save(longTermLiability);
    }

    public List<LongTermLiability> getLiability(String loanId){
        return longTermLiabilityRepository.findLongTermLiabilityByLoanId(loanId);
    }

    public void deleteLiability(String id){
        longTermLiabilityRepository.deleteById(id);
    }

}
