package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.ShortTermLiability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShortTermLiabilityRepository extends JpaRepository<ShortTermLiability, String> {
    List<ShortTermLiability> findShortTermLiabilityByLoanId(String loanId);
}
