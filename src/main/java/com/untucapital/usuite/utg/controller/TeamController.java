package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.Team;
import com.untucapital.usuite.utg.model.User;
import com.untucapital.usuite.utg.repository.TeamRepository;
import com.untucapital.usuite.utg.repository.UserRepository;
import com.untucapital.usuite.utg.service.TeamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public void saveTeam(@RequestBody Team team){
        teamsService.saveTeam(team);
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
    public ResponseEntity<String> updateTeam(@PathVariable("teamId") String id, @RequestBody Team team){
        Team updateTeam  = teamRepository.getTeamById(id);
        updateTeam.setPosition(team.getPosition());
        updateTeam.setDescription(team.getDescription());
        updateTeam.setImageUpload(team.getImageUpload());
        teamRepository.save(updateTeam);
        return  new ResponseEntity<String>("Team Management Successfully Updated", HttpStatus.OK);
    }


}
