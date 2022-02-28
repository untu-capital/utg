package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.Branches;
import com.untucapital.usuite.utg.model.Cities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branches, String> {
    void deleteById(String id);
}
