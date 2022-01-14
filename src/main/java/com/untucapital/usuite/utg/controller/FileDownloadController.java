package com.untucapital.usuite.utg.controller;

import javax.servlet.http.HttpServletRequest;

import com.untucapital.usuite.utg.model.ClientLoan;
import com.untucapital.usuite.utg.model.DatabaseFile;
import com.untucapital.usuite.utg.repository.ClientRepository;
import com.untucapital.usuite.utg.repository.DatabaseFileRepository;
import com.untucapital.usuite.utg.service.DatabaseFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/downloadFiles")
    public ResponseEntity<List<DatabaseFile>> getUploadFilesByUserId(@RequestParam String userId) {
        return new ResponseEntity<List<DatabaseFile>>(databaseFileRepository.findByUserId(userId), HttpStatus.OK);
    }

//    @GetMapping("/downloadFiless")
//    public ResponseEntity<DatabaseFileService> getUploadFilesByFileId(@RequestParam String fileId) {
//        return new ResponseEntity<DatabaseFileService>(fileStorageService.getFile(fileId),HttpStatus.OK);
//    }

}
