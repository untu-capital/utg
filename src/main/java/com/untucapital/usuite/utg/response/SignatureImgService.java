package com.untucapital.usuite.utg.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

@Service
public class SignatureImgService {
    public final Path fileStorageLocation;

    @Autowired
    public SignatureImgService(Environment env) {
        this.fileStorageLocation = Paths.get(env.getProperty("app.file.upload=dir", "./uploads/files"))
                .toAbsolutePath().normalize();
        try{
            Files.createDirectories((this.fileStorageLocation));
        } catch (IOException e) {
            throw new RuntimeException("Could not create the directory where the files will be stored.", e);
        }
    }
    public String getImageUrl(String fileName){
        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/uploads/files/")
                .path(fileName)
                .toUriString();

        return uri;
    }
    public String saveFile(MultipartFile file) {
        String fileName = new Date().getTime()+"-"+file.getOriginalFilename();
        fileName = fileName.replaceAll(" ", "-");
        String imageUrl = getImageUrl(fileName);

        try{
            if(fileName.contains("..")){
                throw new RuntimeException(
                        "Sorry File name contains invalid paths sequence"+fileName
                );
            }

            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return imageUrl;

        }catch (IOException ex){
            throw new RuntimeException("Could not store file"+fileName+".Please try again!", ex);
        }

    }

}
