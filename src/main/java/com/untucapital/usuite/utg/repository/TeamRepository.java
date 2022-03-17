package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.Team;
import com.untucapital.usuite.utg.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, String> {
    Team getTeamById (String id);
}

