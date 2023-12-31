package com.untucapital.usuite.utg.micro.qualitativeAssesment.repository;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.model.SwotRiskAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SwotRiskAnalysisRepository extends JpaRepository<SwotRiskAnalysis, String> {
    List<SwotRiskAnalysis> findAllByLoanId(String id);
}
