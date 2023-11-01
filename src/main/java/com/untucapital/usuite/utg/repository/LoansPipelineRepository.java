package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.LoansPipeline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoansPipelineRepository extends JpaRepository<LoansPipeline, Long> {
    int countByLoanOfficerAndLoanStatus(String loanOfficer, String loanStatus);
}
