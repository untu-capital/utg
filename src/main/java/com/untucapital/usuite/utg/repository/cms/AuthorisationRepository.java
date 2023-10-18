package com.untucapital.usuite.utg.repository.cms;

import com.untucapital.usuite.utg.model.cms.Authorisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorisationRepository extends JpaRepository<Authorisation, String> {
    void deleteById(String id);

    Authorisation findAuthorisationById(String id);

    Optional<Authorisation> findByUserId(String id);

    List<Authorisation> findAllByBranchIdAndAuthLevel(String branchId, String authLevel);

    List<Authorisation> findAllByUserId(String userId);
}
