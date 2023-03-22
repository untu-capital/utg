package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.CollateralSecurity;
import com.untucapital.usuite.utg.model.RequestCollateralSecurity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestCollateraiSecurityRepository extends JpaRepository<RequestCollateralSecurity, String> {

//    List<RequestCollateraiSecurityRepository> findRequestCollateralSecurityBy(String loanId);
}
