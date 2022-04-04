package com.untucapital.usuite.utg.micro.qualitativeAssesment.repository;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.model.LoanOfficerProposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanOfficerProposalRepository extends JpaRepository<LoanOfficerProposal, String> {
    List<LoanOfficerProposal> findAllByLoanId(String id);
}
