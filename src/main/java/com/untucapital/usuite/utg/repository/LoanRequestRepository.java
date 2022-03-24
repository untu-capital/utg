package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.LoanRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRequestRepository extends JpaRepository<LoanRequest, String> {
    List<LoanRequest> findLoanRequestByLoanId(String loanId);
}
