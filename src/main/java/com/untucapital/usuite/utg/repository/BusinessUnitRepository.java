package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.BusinessUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BusinessUnitRepository extends JpaRepository <BusinessUnit, String> {

    //Find Business Unit By id
    List<BusinessUnit> findBusinessUnitById(String id);

    //Find Business Unit By Loan id
    List<BusinessUnit> findBusinessUnitByLoanId(String loanId);


}
