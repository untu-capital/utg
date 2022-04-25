package com.untucapital.usuite.utg.micro.qualitativeAssesment.repository;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.model.ClientCharacterAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientCharacterAssessmentRepository extends JpaRepository<ClientCharacterAssessment, String> {
    List<ClientCharacterAssessment> findAllByLoanId(String id);
}
