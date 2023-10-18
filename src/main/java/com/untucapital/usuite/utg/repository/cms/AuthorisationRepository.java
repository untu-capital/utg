package com.untucapital.usuite.utg.repository.cms;

import com.untucapital.usuite.utg.model.Branches;
import com.untucapital.usuite.utg.model.cms.Authorisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorisationRepository extends JpaRepository<Authorisation, String> {
    void deleteById(String id);

    Authorisation findAuthorisationById(String id);

    List<Authorisation> findAuthorisationByBranchId(String branchId);
}
