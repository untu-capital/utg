package com.untucapital.usuite.utg.untu_capital.repository;

import com.untucapital.usuite.utg.untu_capital.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<Director, String> {
    Director getDirectorById(String id);
}
