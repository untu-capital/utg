package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.LoanRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRequestRepository extends JpaRepository<LoanRequest, String> {
    LoanRequest findByLoanId(String loanId);
}
