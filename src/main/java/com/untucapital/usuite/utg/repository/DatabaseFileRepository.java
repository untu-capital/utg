package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.DatabaseFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface DatabaseFileRepository extends JpaRepository<DatabaseFile, String> {

}