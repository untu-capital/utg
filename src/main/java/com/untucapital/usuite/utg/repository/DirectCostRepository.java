package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.DirectCost;
import com.untucapital.usuite.utg.model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DirectCostRepository extends JpaRepository<DirectCost, String> {

    @Modifying
    @Query(value = "select * from direct_cost where loan_id = :loanId", nativeQuery = true)
    List<DirectCost> findbyLoanId(String loanId);


}
