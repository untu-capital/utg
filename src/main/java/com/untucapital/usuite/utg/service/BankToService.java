package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.BankTo;
import com.untucapital.usuite.utg.repository.BankToRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@javax.transaction.Transactional
public class BankToService {
    @Autowired
    private final BankToRepository bankToRepository;

    public BankToService(BankToRepository bankToRepository) {
        this.bankToRepository = bankToRepository;
    }

    @Transactional(value = "transactionManager")
    public List<BankTo> listAllBankTo() {
        return bankToRepository.findAll();
    }

    @Transactional(value = "transactionManager")
    public void saveBankTo(BankTo bankTo) {
        bankToRepository.save(bankTo);
    }

    //Get List of Business Units by Id
    @Transactional(value = "transactionManager")
    public List<BankTo> listBankToByLoanId(String id, String bank){
        return bankToRepository.findBankToByLoanIdAndBankOrderByMonthAsc(id, bank);
    }

    @Transactional(value = "transactionManager")
    public void deleteById(String id) {
        bankToRepository.deleteById(id);
    }
}
