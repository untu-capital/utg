package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.CreditHistory;
import com.untucapital.usuite.utg.model.LongTermCreditHistory;
import com.untucapital.usuite.utg.repository.LongTermCreditHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class LongTermCreditHistoryService {
    @Autowired
    LongTermCreditHistoryRepository longTermCreditHistoryRepository;

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public void saveCreditHistory(LongTermCreditHistory creditHistory){
        longTermCreditHistoryRepository.save(creditHistory);
    }

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public List<LongTermCreditHistory> getCreditHistoryByLoanId(String loanId){
        return longTermCreditHistoryRepository.findByLoanId(loanId);
    }

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public void deleteCreditHistory(String id){
        longTermCreditHistoryRepository.deleteById(id);
    }
}
