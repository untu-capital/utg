package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.AppraisalFileUpload;
import com.untucapital.usuite.utg.repository.AppraisalFileUploadRepository;
import com.untucapital.usuite.utg.service.AppraisalFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("appraisalFileUpload")
public class AppraisalFileUploadController {
    @Autowired
    private final AppraisalFileUploadService appraisalFileUploadService;

    @Autowired
    private final AppraisalFileUploadRepository appraisalFileUploadRepository;

    public AppraisalFileUploadController(AppraisalFileUploadService appraisalFileUploadService, AppraisalFileUploadRepository appraisalFileUploadRepository) {
        this.appraisalFileUploadService = appraisalFileUploadService;
        this.appraisalFileUploadRepository = appraisalFileUploadRepository;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody AppraisalFileUpload appraisalFileUpload){
        appraisalFileUploadService.save(appraisalFileUpload);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Update existing Appraisal file
    @PutMapping("/update/{loanId}")
    public ResponseEntity<String> updateAppraisalFile(@PathVariable String loanId, @RequestBody AppraisalFileUpload appraisalFileUpload){
        AppraisalFileUpload updateAppraisalFile = appraisalFileUploadRepository.findAppraisalFileUploadByLoanId(loanId);
        updateAppraisalFile.setFileName(appraisalFileUpload.getFileName());
        appraisalFileUploadService.save(updateAppraisalFile);
        return new ResponseEntity<String>("Appraisal File updated", HttpStatus.OK);
    }

    @GetMapping("get/{loanId}")
    public List<AppraisalFileUpload> findByLoanId(@PathVariable String loanId){
        return appraisalFileUploadService.findByLoanId(loanId);
    }

}
