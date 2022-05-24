package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.XdsFileUpload;
import com.untucapital.usuite.utg.repository.XdsFileUploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class XdsFileUploadService {

    @Autowired
    XdsFileUploadRepository xdsFileUploadRepository;

    public void save(XdsFileUpload xdsFileUpload){
        xdsFileUploadRepository.save(xdsFileUpload);
    }

    public XdsFileUpload getXdsFileUpload(String loanId){
        return xdsFileUploadRepository.findXdsFileUploadByLoanId(loanId);
    }
}
