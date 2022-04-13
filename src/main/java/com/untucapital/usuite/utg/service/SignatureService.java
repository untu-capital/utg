//package com.untucapital.usuite.utg.service;
//
//import com.untucapital.usuite.utg.model.Signature;
//import com.untucapital.usuite.utg.repository.SignatureRepository;
//import com.untucapital.usuite.utg.response.GlobalImageService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.transaction.Transactional;
//import java.util.List;
//import java.util.Optional;
//
//@Transactional
//@Service
//public class    SignatureService {
//    @Autowired
//    private final GlobalImageService globalImageService;
//    @Autowired
//    private  final SignatureRepository signatureRepository;
//
//    public SignatureService(GlobalImageService globalImageService, SignatureRepository signatureRepository) {
//        this.globalImageService = globalImageService;
//        this.signatureRepository = signatureRepository;
//    }
//      public List<Signature> getSignature(){
//        return signatureRepository.findAll();
//    }
//
//    public Optional<Signature> getSignature(String id){
//        return signatureRepository.findById(id);
//    }
//
//    public void deleteSignature(String id){
//        signatureRepository.deleteById(id);
//    }
//
//    public String saveSignature(MultipartFile file, String fullName, String position, String branch) {
//        Signature signature = new Signature();
//        String imageUrl= globalImageService.saveFile(file);
//
//        signature.setFullName(fullName);
//        signature.setPosition(position);
//        signature.setBranch(branch);
//        signature.setImageUrl(imageUrl);
//
//        signatureRepository.save(signature);
//
//        return imageUrl;
//    }
//}
