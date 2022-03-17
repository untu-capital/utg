package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.Director;
import com.untucapital.usuite.utg.model.Team;
import com.untucapital.usuite.utg.repository.DirectorRepository;
import com.untucapital.usuite.utg.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping( path = "directors")
public class DirectorController {
    @Autowired
    DirectorRepository directorRepository;

    @Autowired
    private final DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    //Save Director
    @PostMapping("/save")
    public void saveDirector(@RequestBody Director director){
        directorService.saveDirector(director);
    }
    //Get Director
    @GetMapping("/get")
    public List<Director> getDirectors(){
        return directorService.getDirectors();
    }
    //Get Director by id
    @GetMapping("/get/{directorId}")
    public Optional<Director> getDirector(@PathVariable("directorId") String id){
        return directorService.getDirector(id);
    }
    //Delete Director
    @DeleteMapping("/delete/{directorId}")
    public void deleteDirector(@PathVariable("directorId") String id){
        directorService.deleteDirector(id);
    }
    //Update Director
    @PutMapping("/update/{directorId}")
    public ResponseEntity<String> updateTeam(@PathVariable("directorId") String id, @RequestBody Director director){
        Director updateDirector  = directorRepository.getDirectorById(id);
        updateDirector.setPosition(director.getPosition());
        updateDirector.setDescription(director.getDescription());
        updateDirector.setImageUpload(director.getImageUpload());
        directorRepository.save(updateDirector);
        return  new ResponseEntity<String>("Team Management Successfully Updated", HttpStatus.OK);
    }

}
