package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.AssessmentFileUpload;
import com.untucapital.usuite.utg.repository.AssessmentFileUploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class AssessmentFileUploadService {

    @Autowired
    AssessmentFileUploadRepository assessmentFileUploadRepository;

    public void save(AssessmentFileUpload assessmentFileUpload){
        assessmentFileUploadRepository.save(assessmentFileUpload);
    }

    public List<AssessmentFileUpload> getAssessmentFileUpload(String loanId){
        return assessmentFileUploadRepository.findAssessmentFileUploadByLoanId(loanId);

    }

//    public void deleteAssessmentFileUpload();
}
