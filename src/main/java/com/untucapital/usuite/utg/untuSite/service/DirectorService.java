package com.untucapital.usuite.utg.untuSite.service;

import com.untucapital.usuite.utg.untuSite.model.Director;
import com.untucapital.usuite.utg.untuSite.repository.DirectorRepository;
import com.untucapital.usuite.utg.untuSite.response.GlobalImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class DirectorService {
    @Autowired
    private final GlobalImageService globalImageService;
    @Autowired
    private final DirectorRepository directorRepository;

    public DirectorService(GlobalImageService globalImageService, DirectorRepository directorRepository) {
        this.globalImageService = globalImageService;
        this.directorRepository = directorRepository;
    }

    //Get List of Directors
    public List<Director> getDirectors(){
        return directorRepository.findAll();
    }
    //Get Director by Id
    public Optional<Director> getDirector(String id){
        return directorRepository.findById(id);
    }
    //Delete Director By Id
    public void deleteDirector(String id){
        directorRepository.deleteById(id);
    }
    //Save Director
    public String saveDirector(MultipartFile file, String name, String position, String description) {
        Director director= new Director();
        String imageUrl= globalImageService.saveFile(file);

        director.setName(name);
        director.setPosition(position);
        director.setDescription(description);
        director.setImageUrl(imageUrl);

        directorRepository.save(director);

        return imageUrl;
    }
}
