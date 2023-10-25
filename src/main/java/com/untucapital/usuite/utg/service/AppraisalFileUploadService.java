package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.AppraisalFileUpload;
import com.untucapital.usuite.utg.repository.AppraisalFileUploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppraisalFileUploadService {
    @Autowired
    private final AppraisalFileUploadRepository appraisalFileUploadRepository;

    public AppraisalFileUploadService(AppraisalFileUploadRepository appraisalFileUploadRepository) {
        this.appraisalFileUploadRepository = appraisalFileUploadRepository;
    }

    @Transactional(value = "transactionManager")
    public void save(AppraisalFileUpload appraisalFileUpload){
        appraisalFileUploadRepository.save(appraisalFileUpload);
    }

    @Transactional(value = "transactionManager")
    public void delete(String id){
        appraisalFileUploadRepository.deleteById(id);
    }

    @Transactional(value = "transactionManager")
    public List<AppraisalFileUpload> findByLoanId(String loanId) {
        return appraisalFileUploadRepository.findByLoanId(loanId);
    }
}
