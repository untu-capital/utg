package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.LongTermLiability;
import com.untucapital.usuite.utg.repository.LongTermLiabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@javax.transaction.Transactional
public class LongTermLiabilityService {
    @Autowired
    private final LongTermLiabilityRepository longTermLiabilityRepository;


    public LongTermLiabilityService(LongTermLiabilityRepository longTermLiabilityRepository) {
        this.longTermLiabilityRepository = longTermLiabilityRepository;
    }

    @Transactional(value = "transactionManager")
    public void saveLiability(LongTermLiability longTermLiability){
        longTermLiabilityRepository.save(longTermLiability);
    }

    @Transactional(value = "transactionManager")
    public List<LongTermLiability> getLiability(String loanId){
        return longTermLiabilityRepository.findLongTermLiabilityByLoanId(loanId);
    }

    @Transactional(value = "transactionManager")
    public void deleteLiability(String id){
        longTermLiabilityRepository.deleteById(id);
    }

}
