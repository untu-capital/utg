package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.AssessmentFileUpload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AssessmentFileUploadRepository extends JpaRepository<AssessmentFileUpload, String> {

    List<AssessmentFileUpload> findAssessmentFileUploadByLoanId(String loanId);


}
