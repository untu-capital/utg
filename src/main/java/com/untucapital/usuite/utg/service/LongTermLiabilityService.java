package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.LongTermLiability;
import com.untucapital.usuite.utg.model.ShortTermLiability;
import com.untucapital.usuite.utg.repository.LongTermCreditHistoryRepository;
import com.untucapital.usuite.utg.repository.LongTermLiabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LongTermLiabilityService {
    @Autowired
    LongTermLiabilityRepository longTermLiabilityRepository;

    public void saveLiability(LongTermLiability longTermLiability){
        longTermLiabilityRepository.save(longTermLiability);
    }

    public List<LongTermLiability> getLiability(String loanId){
        return longTermLiabilityRepository.findLongTermLiabilityByLoanId(loanId);
    }

    public void deleteLiablity(String id){
        longTermLiabilityRepository.deleteById(id);
    }

}
