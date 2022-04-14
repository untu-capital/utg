package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.LongTermLiability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LongTermLiabilityRepository extends JpaRepository<LongTermLiability, String> {
    List<LongTermLiability> findLongTermLiabilityByLoanId(String loanId);
}
