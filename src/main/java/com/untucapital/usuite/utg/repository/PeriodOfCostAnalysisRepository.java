package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.PeriodOfCostAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PeriodOfCostAnalysisRepository extends JpaRepository<PeriodOfCostAnalysis, String> {

    @Modifying
    @Query(value = "select * from period_of_analysis where loan_id = :loanId", nativeQuery = true)
    List<PeriodOfCostAnalysis> findByLoanId(String loanId);

    @Modifying
    @Query(value = "select * from period_of_analysis where loan_id = :loanId", nativeQuery = true)
    boolean existByLoanId(String loanId);
}
