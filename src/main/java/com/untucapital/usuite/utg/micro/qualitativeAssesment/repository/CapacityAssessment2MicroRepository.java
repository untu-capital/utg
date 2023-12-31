package com.untucapital.usuite.utg.micro.qualitativeAssesment.repository;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.model.CapacityAssessment2Micro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CapacityAssessment2MicroRepository extends JpaRepository<CapacityAssessment2Micro, String> {
    List<CapacityAssessment2Micro> findAllByLoanId(String id);
}
