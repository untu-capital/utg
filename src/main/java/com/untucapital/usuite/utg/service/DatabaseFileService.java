package com.untucapital.usuite.utg.service;

import java.io.IOException;

import com.untucapital.usuite.utg.exception.FileNotFoundException;
import com.untucapital.usuite.utg.exception.FileStorageException;
import com.untucapital.usuite.utg.model.DatabaseFile;
import com.untucapital.usuite.utg.repository.DatabaseFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DatabaseFileService {

    @Autowired
    private DatabaseFileRepository dbFileRepository;

    public DatabaseFile storeFile(MultipartFile file, String description, String userId) {

        DatabaseFile d = new DatabaseFile();
        d.setFileDescription(description);
        d.setUserId(userId);
//        d.setLoanId(loanId);
        //dbFileRepository.save(d);

        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            DatabaseFile dbFile = new DatabaseFile(fileName,file.getContentType(),description, userId,file.getBytes());

            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public DatabaseFile getFile(String fileId) {
        return dbFileRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found with id " + fileId));
    }
}
