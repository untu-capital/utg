package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.CollateralSecurity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollateraiSecurityRepository extends JpaRepository<CollateralSecurity, String> {
    List<CollateralSecurity> findCollateralSecurityByLoanId(String loanId);
}
