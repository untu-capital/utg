package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankRepository extends JpaRepository<Bank, String> {
    List<Bank> findByLoanId(String loanId);
}
