package com.untucapital.usuite.utg.service.cms;

import com.untucapital.usuite.utg.model.cms.CmsVaultPermission;
import com.untucapital.usuite.utg.repository.cms.CmsVaultPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CmsVaultPermissionService {

    @Autowired
    private CmsVaultPermissionRepository cmsVaultPermissionRepository;

    public List<CmsVaultPermission> getAllCmsVaultPermissions() {
        return cmsVaultPermissionRepository.findAll();
    }

    public void saveCmsVaultPermission(CmsVaultPermission cmsVaultPermission) {
        cmsVaultPermissionRepository.save(cmsVaultPermission);
    }

    public Optional<CmsVaultPermission> getCmsVaultPermissionById(String id) {
        return cmsVaultPermissionRepository.findById(id);
    }

    public Optional<CmsVaultPermission> getCmsVaultPermissionByPoNumber(String id) {
        return cmsVaultPermissionRepository.getCmsVaultPermissionById(id);
    }

    public List<CmsVaultPermission> getCmsVaultPermissionByUserId(String userId) {
        return cmsVaultPermissionRepository.findCmsVaultPermissionsByUserid(userId);
    }


    public void deleteCmsVaultPermission(String id) {
        cmsVaultPermissionRepository.deleteById(id);
    }

//    public List<CmsVaultPermission> getCmsVaultPermissionByVaultAccCode(String vaultAccCode) {
//        return cmsVaultPermissionRepository.getCmsVaultPermissionsByVault_acc_code(vaultAccCode);
//    }
}
