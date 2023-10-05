package com.untucapital.usuite.utg.repository.cms;

import com.untucapital.usuite.utg.model.Requisitions;
import com.untucapital.usuite.utg.model.cms.CmsVaultPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CmsVaultPermissionRepository extends JpaRepository<CmsVaultPermission, String> {

    Optional<CmsVaultPermission> getCmsVaultPermissionById(String id);

//    List<CmsVaultPermission> getCmsVaultPermissionsByVault_acc_code (String vault_acc);

}
