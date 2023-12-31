package com.untucapital.usuite.utg.micro.qualitativeAssesment.repository;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.model.CapacityAssessmentMicro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CapacityAssessmentMicroRepository extends JpaRepository<CapacityAssessmentMicro, String> {
    List<CapacityAssessmentMicro> findAllByLoanId(String id);
}
