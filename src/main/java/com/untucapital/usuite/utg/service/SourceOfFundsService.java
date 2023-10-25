package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.SourceOfFunds;
import com.untucapital.usuite.utg.repository.SourceOfFundsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@javax.transaction.Transactional
public class SourceOfFundsService {
    @Autowired
    SourceOfFundsRepository sourceOfFundsRepository;

    @Transactional(value = "transactionManager")
    public void saveSourceOfFunds(SourceOfFunds sourceOfFunds){
        sourceOfFundsRepository.save(sourceOfFunds);
    }

    @Transactional(value = "transactionManager")
    public List<SourceOfFunds> getSourceOfFundsByLoanId(String loanId){
        return sourceOfFundsRepository.findByLoanId(loanId);
    }

    @Transactional(value = "transactionManager")
    public void deleteSourceOfFunds(String id){
      sourceOfFundsRepository.deleteById(id);
    }
}
