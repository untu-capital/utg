package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.FinancialInflow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FinancialInflowRepository extends JpaRepository<FinancialInflow, String> {
    List<FinancialInflow> findFinancialInflowByLoanId(String loanId);
}
