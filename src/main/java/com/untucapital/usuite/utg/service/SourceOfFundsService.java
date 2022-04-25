package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.SourceOfFunds;
import com.untucapital.usuite.utg.repository.SourceOfFundsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class SourceOfFundsService {
    @Autowired
    SourceOfFundsRepository sourceOfFundsRepository;

    public void saveSourceOfFunds(SourceOfFunds sourceOfFunds){
        sourceOfFundsRepository.save(sourceOfFunds);
    }

    public List<SourceOfFunds> getSourceOfFundsByLoanId(String loanId){
        return sourceOfFundsRepository.findByLoanId(loanId);
    }

    public void deleteSourceOfFunds(String id){
      sourceOfFundsRepository.deleteById(id);
    }
}
