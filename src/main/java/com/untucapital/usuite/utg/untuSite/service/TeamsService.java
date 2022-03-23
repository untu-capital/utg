package com.untucapital.usuite.utg.untuSite.service;

import com.untucapital.usuite.utg.untuSite.model.Team;
import com.untucapital.usuite.utg.untuSite.repository.TeamRepository;
import com.untucapital.usuite.utg.untuSite.response.GlobalImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class TeamsService {
    @Autowired
    private final GlobalImageService globalImageService;
    @Autowired
    private  final TeamRepository teamRepository;

    public TeamsService(GlobalImageService globalImageService, TeamRepository teamRepository) {
        this.globalImageService = globalImageService;
        this.teamRepository = teamRepository;
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
    //Save
    public String saveTeam(MultipartFile file, String name, String position, String description) {
        Team team = new Team();
        String imageUrl= globalImageService.saveFile(file);

        team.setName(name);
        team.setPosition(position);
        team.setDescription(description);
        team.setImageUrl(imageUrl);

        teamRepository.save(team);

        return imageUrl;
    }
}
