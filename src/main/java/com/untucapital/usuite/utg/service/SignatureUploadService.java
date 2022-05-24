package com.untucapital.usuite.utg.service;
import com.untucapital.usuite.utg.model.Business;
import com.untucapital.usuite.utg.model.SignatureUpload;
import com.untucapital.usuite.utg.repository.SignatureUploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class SignatureUploadService{

    @Autowired
    SignatureUploadRepository signatureUploadRepository;
    public  void save(SignatureUpload signatureUpload){
        signatureUploadRepository.save(signatureUpload);
    }
    public  SignatureUpload getSignatureFile(String userId){
        return signatureUploadRepository.findSignatureUploadByUserId(userId);

    }


    public List<SignatureUpload> listAllSignatureUpload() {
        return signatureUploadRepository.findAll();
    }
}




