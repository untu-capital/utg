package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.CreditHistory;
import com.untucapital.usuite.utg.model.MainCompetitor;
import com.untucapital.usuite.utg.repository.CreditHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class CreditHistoryService {
    @Autowired
    CreditHistoryRepository creditHistoryRepository;

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public void saveCreditHistory(CreditHistory creditHistory){
        creditHistoryRepository.save(creditHistory);
    }

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public List<CreditHistory> getCreditHistoryByLoanId(String loanId){
        return creditHistoryRepository.findByLoanId(loanId);
    }

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public void deleteCreditHistory(String id){
        creditHistoryRepository.deleteById(id);
    }

}
