package com.untucapital.usuite.utg.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.untucapital.usuite.utg.model.DatabaseFile;
import com.untucapital.usuite.utg.payload.Response;
import com.untucapital.usuite.utg.service.DatabaseFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class FileUploadController {

    @Autowired
    private DatabaseFileService fileStorageService;

    @PostMapping("/uploadFile")
    public Response uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("description") String description, @RequestParam("userId") String userId) {
    	DatabaseFile fileName = fileStorageService.storeFile(file, description, userId);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName.getFileName())
                .path(fileName.getFileDescription())
                .path(fileName.getUserId())
                .toUriString();

        return new Response(fileName.getFileName(), fileDownloadUri, file.getContentType(), fileName.getFileDescription(), fileName.getUserId() ,file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public List<Response> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files, @RequestParam("description") String description, @RequestParam("userId") String userId) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file, description, userId))
                .collect(Collectors.toList());
    }
}
