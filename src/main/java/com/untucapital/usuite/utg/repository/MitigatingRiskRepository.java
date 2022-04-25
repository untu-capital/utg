package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.MitigatingRisk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MitigatingRiskRepository extends JpaRepository<MitigatingRisk, String> {
    List<MitigatingRisk> findByLoanId(String loanId);
}
