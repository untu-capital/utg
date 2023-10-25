package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.Bank;
import com.untucapital.usuite.utg.model.Comments;
import com.untucapital.usuite.utg.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;



@Service
@javax.transaction.Transactional
public class BankService {
    @Autowired
    BankRepository bankRepository;

    @Transactional(value = "transactionManager")
    public void  saveBank(Bank bank) {
        bankRepository.save(bank);
    }

    public List<Bank> getByLoanId(String loanId) {
        return bankRepository.findByLoanId(loanId);
    }

}
