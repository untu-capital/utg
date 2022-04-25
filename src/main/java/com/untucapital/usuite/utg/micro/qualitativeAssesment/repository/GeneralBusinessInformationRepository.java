package com.untucapital.usuite.utg.micro.qualitativeAssesment.repository;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.model.GeneralBusinessInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeneralBusinessInformationRepository extends JpaRepository<GeneralBusinessInformation, String> {
    List<GeneralBusinessInformation> findAllByLoanId(String id);
}
