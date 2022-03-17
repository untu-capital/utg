package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.Team;
import com.untucapital.usuite.utg.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamsService {
    @Autowired
    private  final TeamRepository teamRepository;

    public TeamsService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }
    
    //Save Team
    public void saveTeam(Team team){
        teamRepository.save(team);
    }
    //Get Team
    public List<Team> getTeams(){
        return teamRepository.findAll();
    }
    //Get Team by id
    public Optional<Team> getTeam(String id){
        return teamRepository.findById(id);
    }
    //Delete Team
    public void deleteTeam(String id){
        teamRepository.deleteById(id);
    }
}
