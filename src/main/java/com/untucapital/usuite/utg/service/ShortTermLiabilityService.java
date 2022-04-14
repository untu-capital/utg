package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.FixedAsset;
import com.untucapital.usuite.utg.model.ShortTermLiability;
import com.untucapital.usuite.utg.repository.ShortTermLiabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ShortTermLiabilityService {
    @Autowired
    ShortTermLiabilityRepository shortTermLiabilityRepository;

    public void saveLiability(ShortTermLiability shortTermLiability){
        shortTermLiabilityRepository.save(shortTermLiability);
    }

    public List<ShortTermLiability> getLiability(String loanId){
        return shortTermLiabilityRepository.findShortTermLiabilityByLoanId(loanId);
    }

    public void deleteLiablity(String id){
        shortTermLiabilityRepository.deleteById(id);
    }
}
