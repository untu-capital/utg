package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.BankTo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankToRepository extends JpaRepository<BankTo, String> {
    //List<BankTo> findBankToByLoanIdAAndBankOrderByMonthAsc(String loanId, String bank);
    List<BankTo> findBankToByLoanIdAndBankOrderByMonthAsc(String loanId, String bank);
}
