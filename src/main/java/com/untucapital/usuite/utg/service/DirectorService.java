package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.Director;
import com.untucapital.usuite.utg.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DirectorService {
    @Autowired
    private final DirectorRepository directorRepository;

    public DirectorService(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }
    //Save Director
    public void saveDirector(Director director){
        directorRepository.save(director);
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

}
