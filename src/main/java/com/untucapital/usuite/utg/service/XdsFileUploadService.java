package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.XdsFileUpload;
import com.untucapital.usuite.utg.repository.XdsFileUploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@javax.transaction.Transactional
public class XdsFileUploadService {

    @Autowired
    XdsFileUploadRepository xdsFileUploadRepository;

    @Transactional(value = "transactionManager")
    public void save(XdsFileUpload xdsFileUpload){
        xdsFileUploadRepository.save(xdsFileUpload);
    }

    @Transactional(value = "transactionManager")
    public XdsFileUpload getXdsFileUpload(String loanId){
        return xdsFileUploadRepository.findXdsFileUploadByLoanId(loanId);
    }
}
