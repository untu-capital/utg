package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.FixedAsset;
import com.untucapital.usuite.utg.model.ShortTermLiability;
import com.untucapital.usuite.utg.repository.ShortTermLiabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@javax.transaction.Transactional
public class ShortTermLiabilityService {
    @Autowired
    ShortTermLiabilityRepository shortTermLiabilityRepository;

    @Transactional(value = "transactionManager")
    public void saveLiability(ShortTermLiability shortTermLiability){
        shortTermLiabilityRepository.save(shortTermLiability);
    }

    @Transactional(value = "transactionManager")
    public List<ShortTermLiability> getLiability(String loanId){
        return shortTermLiabilityRepository.findShortTermLiabilityByLoanId(loanId);
    }

    @Transactional(value = "transactionManager")
    public void deleteLiablity(String id){
        shortTermLiabilityRepository.deleteById(id);
    }
}
