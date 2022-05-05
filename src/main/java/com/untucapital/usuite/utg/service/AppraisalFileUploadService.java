package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.AppraisalFileUpload;
import com.untucapital.usuite.utg.repository.AppraisalFileUploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppraisalFileUploadService {
    @Autowired
    private final AppraisalFileUploadRepository appraisalFileUploadRepository;

    public AppraisalFileUploadService(AppraisalFileUploadRepository appraisalFileUploadRepository) {
        this.appraisalFileUploadRepository = appraisalFileUploadRepository;
    }

    public void save(AppraisalFileUpload appraisalFileUpload){
        appraisalFileUploadRepository.save(appraisalFileUpload);
    }

    public void delete(String id){
        appraisalFileUploadRepository.deleteById(id);
    }

    public List<AppraisalFileUpload> findByLoanId(String loanId) {
        return appraisalFileUploadRepository.findByLoanId(loanId);
    }
}
