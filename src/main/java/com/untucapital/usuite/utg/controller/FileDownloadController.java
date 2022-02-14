package com.untucapital.usuite.utg.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.untucapital.usuite.utg.model.ClientLoan;
import com.untucapital.usuite.utg.model.DatabaseFile;
import com.untucapital.usuite.utg.payload.Response;
import com.untucapital.usuite.utg.repository.ClientRepository;
import com.untucapital.usuite.utg.repository.DatabaseFileRepository;
import com.untucapital.usuite.utg.service.DatabaseFileService;
import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "credit_application")
public class FileDownloadController {

    @Autowired
    private DatabaseFileService fileStorageService;

    @Autowired
    DatabaseFileRepository databaseFileRepository;

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        DatabaseFile databaseFile = fileStorageService.getFile(fileName);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(databaseFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + databaseFile.getFileName() + "\"")
                .body(new ByteArrayResource(databaseFile.getData()));
    }

    // select files excluding appraisal ************
    @GetMapping("/downloadFiles/{userId}/{appraisal}/{selfie}/{nationalId}")
    public ResponseEntity<List<DatabaseFile>> getUploadFilesByUserId(@PathVariable("userId") String userId, @PathVariable("appraisal") String appraisal, @PathVariable("selfie") String selfie, @PathVariable("nationalId") String nationalId) {
        return new ResponseEntity<List<DatabaseFile>>(databaseFileRepository.findByUserIdAndFileDescriptionNotContainsAndFileDescriptionNotContainsAndFileDescriptionNotContains(userId, appraisal, selfie, nationalId), HttpStatus.OK);
    }

    // select selfie and nationalId files
    @GetMapping("/downloadFiles/{userId}/{loanId}/{fileDescription}")
    public ResponseEntity<List<DatabaseFile>> getLoanFiles(@PathVariable("userId") String userId, @PathVariable("loanId") String loanId, @PathVariable("fileDescription") String fileDescription) {
        return new ResponseEntity<List<DatabaseFile>>(databaseFileRepository.findByUserIdAndLoanIdAndFileDescriptionNotContains(userId, loanId, fileDescription), HttpStatus.OK);
    }

    // Excel appraisal.. select using userid and description = appraisal
    @GetMapping("/appraisal/{userId}/{loanId}/{fileDescription}")
    public ResponseEntity<List<DatabaseFile>> getUploadFilesByAppraisal(@PathVariable("userId") String userId, @PathVariable("loanId") String loanId, @PathVariable("fileDescription") String fileDescription) {
        return new ResponseEntity<List<DatabaseFile>>(databaseFileRepository.findByUserIdAndLoanIdAndFileDescription(userId, loanId, fileDescription), HttpStatus.OK);
    }

}
