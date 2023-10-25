package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.AssessmentFileUpload;
import com.untucapital.usuite.utg.repository.AssessmentFileUploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@javax.transaction.Transactional
public class AssessmentFileUploadService {

    @Autowired
    AssessmentFileUploadRepository assessmentFileUploadRepository;

    @Transactional(value = "transactionManager")
    public void save(AssessmentFileUpload assessmentFileUpload){
        assessmentFileUploadRepository.save(assessmentFileUpload);
    }

    @Transactional(value = "transactionManager")
    public List<AssessmentFileUpload> getAssessmentFileUpload(String loanId){
        return assessmentFileUploadRepository.findAssessmentFileUploadByLoanId(loanId);

    }

//    public void deleteAssessmentFileUpload();
}
