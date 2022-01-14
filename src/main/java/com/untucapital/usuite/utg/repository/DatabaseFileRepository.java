package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.ClientLoan;
import com.untucapital.usuite.utg.model.DatabaseFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DatabaseFileRepository extends JpaRepository<DatabaseFile, String> {

    List<DatabaseFile> findByUserId(String userId);

//    DatabaseFile findByFileId(String fileId);

}