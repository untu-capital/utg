package com.untucapital.usuite.utg.repository.cms;

import com.untucapital.usuite.utg.model.Branches;
import com.untucapital.usuite.utg.model.cms.Vault;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author tjchidanika
 * @created 27/9/2023
 */

@Repository
public interface VaultRepository extends JpaRepository<Vault, Integer> {

    Optional<Vault> findVaultByBranch_BranchNameAndType(String branchName, String type);

    List<Vault> findVaultByBranch_BranchName(String branch);
    List<Vault> findByBranch(Branches branch);

}
