package com.untucapital.usuite.utg.service;
import com.untucapital.usuite.utg.model.SignatureUpload;
import com.untucapital.usuite.utg.repository.SignatureUploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@javax.transaction.Transactional
public class SignatureUploadService{

    @Autowired
    SignatureUploadRepository signatureUploadRepository;

    @Transactional(value = "transactionManager")
    public  void save(SignatureUpload signatureUpload){
        signatureUploadRepository.save(signatureUpload);
    }

    @Transactional(value = "transactionManager")
    public  SignatureUpload getSignatureFile(String userId){
        return signatureUploadRepository.findSignatureUploadByUserId(userId);

    }

    @Transactional(value = "transactionManager")
    public List<SignatureUpload> listAllSignatureUpload() {
        return signatureUploadRepository.findAll();
    }
}




