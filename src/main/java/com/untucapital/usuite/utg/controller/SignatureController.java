package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.Signature;
import com.untucapital.usuite.utg.repository.SignatureRepository;
import com.untucapital.usuite.utg.response.SignatureResponse;
import com.untucapital.usuite.utg.service.SignatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "signature")
public class SignatureController {
    @Autowired
    SignatureRepository signatureRepository;

    @Autowired
    private final SignatureService signatureService;
    public SignatureController(SignatureService signatureService) {
        this.signatureService = signatureService;
    }


    @PostMapping("/save")
    public ResponseEntity<SignatureResponse> saveSignature(
            @RequestParam(name = "file", required = false) MultipartFile file,
            @RequestParam("fullName") String fullName,
            @RequestParam("position") String position,
            @RequestParam("branch") String branch
    ){
        String fileName = signatureService.saveSignature(file, fullName, position, branch);
        SignatureResponse signatureResponse = new SignatureResponse(fileName);
        return ResponseEntity.ok().body(signatureResponse);
    }

    @GetMapping("/signatures")
    public List<Signature> getSignature(){
        return  signatureService.getSignature();
    }

    @GetMapping("/signature/{id}")
    public Optional<Signature> getSignature(@PathVariable("id") String id){
        return signatureService.getSignature(id);
    }

    @DeleteMapping("/delete/{id}")
    public  void deleteSignature(@PathVariable("id") String id){
        signatureService.deleteSignature(id);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateSignature(

            @PathVariable("id") String id,
            @RequestParam("position") String position,
            @RequestParam("branch") String branch){

        Signature updateSignature  = signatureRepository.getSignatureById(id);
        updateSignature.setPosition(position);
        updateSignature.setBranch(branch);

        signatureRepository.save(updateSignature);
        return  new ResponseEntity<String>("Signature Updated", HttpStatus.OK);
    }


}
