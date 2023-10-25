package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.Signature;
import com.untucapital.usuite.utg.repository.SignatureRepository;
import com.untucapital.usuite.utg.response.SignatureImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@javax.transaction.Transactional
public class    SignatureService {
    @Autowired
    private final SignatureImgService signatureImgService;
    @Autowired
    private  final SignatureRepository signatureRepository;

    public SignatureService(SignatureImgService signatureImgService, SignatureRepository signatureRepository) {
        this.signatureImgService = signatureImgService;
        this.signatureRepository = signatureRepository;
    }

    @Transactional(value = "transactionManager")
      public List<Signature> getSignature(){
        return signatureRepository.findAll();
    }

    @Transactional(value = "transactionManager")
    public Optional<Signature> getSignature(String id){
        return signatureRepository.findById(id);
    }

    @Transactional(value = "transactionManager")
    public void deleteSignature(String id){
        signatureRepository.deleteById(id);
    }

    @Transactional(value = "transactionManager")
    public String saveSignature(MultipartFile file, String fullName, String position, String branch) {
        Signature signature = new Signature();
        String imageUrl= signatureImgService.saveFile(file);

        signature.setFullName(fullName);
        signature.setPosition(position);
        signature.setBranch(branch);
        signature.setImageUrl(imageUrl);

        signatureRepository.save(signature);

        return imageUrl;
    }
}
