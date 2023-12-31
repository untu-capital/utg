package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.BankTo;
import com.untucapital.usuite.utg.repository.BankToRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BankToService {
    @Autowired
    private final BankToRepository bankToRepository;

    public BankToService(BankToRepository bankToRepository) {
        this.bankToRepository = bankToRepository;
    }

    public List<BankTo> listAllBankTo() {
        return bankToRepository.findAll();
    }

    public void saveBankTo(BankTo bankTo) {
        bankToRepository.save(bankTo);
    }

    //Get List of Business Units by Id
    public List<BankTo> listBankToByLoanId(String id, String bank){
        return bankToRepository.findBankToByLoanIdAndBankOrderByMonthAsc(id, bank);
    }

    public void deleteById(String id) {
        bankToRepository.deleteById(id);
    }
}
