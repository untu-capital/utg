package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.XdsFileUpload;
import com.untucapital.usuite.utg.repository.XdsFileUploadRepository;
import com.untucapital.usuite.utg.service.XdsFileUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/xdsFileUpload")
public class XdsFileUploadController {

    @Autowired
    XdsFileUploadService xdsFileUploadService;

    @Autowired
    XdsFileUploadRepository xdsFileUploadRepository;

    private static final Logger log = LoggerFactory.getLogger(XdsFileUploadController.class);

    // Add XDS file first time
    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody XdsFileUpload xdsFileUpload){
        xdsFileUploadService.save(xdsFileUpload);
        return new ResponseEntity<String>("XDS File uploaded", HttpStatus.OK);
    }

    // Update existing XDS file
    @PutMapping("/update/{loanId}")
    public ResponseEntity<String> updateXdsFile(@PathVariable String loanId, @RequestBody XdsFileUpload xdsFileUpload){
        XdsFileUpload updateXdsFile = xdsFileUploadRepository.findXdsFileUploadByLoanId(loanId);
        updateXdsFile.setFileName(xdsFileUpload.getFileName());
        xdsFileUploadService.save(updateXdsFile);
        return new ResponseEntity<String>("XDS File updated", HttpStatus.OK);
    }

    @GetMapping("/get/{loanId}")
    public XdsFileUpload getXdsFile(@PathVariable String loanId){
        return xdsFileUploadService.getXdsFileUpload(loanId);
    }
}
