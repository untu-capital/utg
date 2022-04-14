package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.InterestAndFinancialCost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterestAndFinancialCostRepository extends JpaRepository<InterestAndFinancialCost, String> {
    List<InterestAndFinancialCost> findInterestAndFinancialCostByLoanId(String loanId);
}
