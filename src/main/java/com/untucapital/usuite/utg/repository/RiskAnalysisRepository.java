package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.RiskAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RiskAnalysisRepository extends JpaRepository<RiskAnalysis, String> {
    List<RiskAnalysis> findByLoanId(String loanId);
}
