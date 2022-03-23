package com.untucapital.usuite.utg.untuSite.controller;

import com.untucapital.usuite.utg.untuSite.model.Team;
import com.untucapital.usuite.utg.untuSite.repository.TeamRepository;
import com.untucapital.usuite.utg.untuSite.response.GlobalImageResponse;
import com.untucapital.usuite.utg.untuSite.service.TeamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "teams")
public class TeamController {
    @Autowired
    TeamRepository teamRepository;

    @Autowired
    private final TeamsService teamsService;
    public TeamController(TeamsService teamsService) {
        this.teamsService = teamsService;
    }

    //Save Team
    @PostMapping("/save")
    public ResponseEntity<GlobalImageResponse> saveTeam(
            @RequestParam(name = "file", required = false) MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("position") String position,
            @RequestParam("description") String description
    ){
        String fileName = teamsService.saveTeam(file, name, position, description);
        GlobalImageResponse globalImageResponse = new GlobalImageResponse(fileName);
        return ResponseEntity.ok().body(globalImageResponse);
    }
    //Get Teams
    @GetMapping("/get")
    public List<Team> getTeams(){
        return  teamsService.getTeams();
    }
    //Get Team by Id
    @GetMapping("/get/{teamId}")
    public Optional<Team> getTeam(@PathVariable("teamId") String id){
        return teamsService.getTeam(id);
    }
    //Delete Team
    @DeleteMapping("/delete/{teamId}")
    public  void deleteTeam(@PathVariable("teamId") String id){
        teamsService.deleteTeam(id);
    }

    //Update Team
    @PutMapping("/update/{teamId}")
    public ResponseEntity<String> updateTeam(

            @PathVariable("teamId") String id,
            @RequestParam("position") String position,
            @RequestParam("description") String description){

        Team updateTeam  = teamRepository.getTeamById(id);
        updateTeam.setPosition(position);
        updateTeam.setDescription(description);

        teamRepository.save(updateTeam);
        return  new ResponseEntity<String>("Team Management Successfully Updated", HttpStatus.OK);
    }


}
