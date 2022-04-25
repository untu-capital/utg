package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.SourceOfFunds;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SourceOfFundsRepository extends JpaRepository<SourceOfFunds, String> {
    List<SourceOfFunds> findByLoanId(String loanId);
}
