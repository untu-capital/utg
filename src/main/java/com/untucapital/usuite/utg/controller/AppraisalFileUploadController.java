package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.AppraisalFileUpload;
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

    public AppraisalFileUploadController(AppraisalFileUploadService appraisalFileUploadService) {
        this.appraisalFileUploadService = appraisalFileUploadService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody AppraisalFileUpload appraisalFileUpload){
        appraisalFileUploadService.save(appraisalFileUpload);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("get/{loanId}")
    public List<AppraisalFileUpload> findByLoanId(@PathVariable String loanId){
        return appraisalFileUploadService.findByLoanId(loanId);
    }

}
