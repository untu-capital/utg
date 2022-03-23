package com.untucapital.usuite.utg.untuSite.repository;

import com.untucapital.usuite.utg.untuSite.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, String> {
    Team getTeamById (String id);
}

