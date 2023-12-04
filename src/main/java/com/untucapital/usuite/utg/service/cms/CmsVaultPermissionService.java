package com.untucapital.usuite.utg.service.cms;

import com.untucapital.usuite.utg.model.cms.CmsVaultPermission;
import com.untucapital.usuite.utg.model.cms.Vault;
import com.untucapital.usuite.utg.repository.cms.CmsVaultPermissionRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@javax.transaction.Transactional
@Slf4j
public class CmsVaultPermissionService {

    @Autowired
    private CmsVaultPermissionRepository cmsVaultPermissionRepository;

    @Autowired
    private VaultService vaultService;

    @Transactional(value = "transactionManager")
    public List<CmsVaultPermission> getAllCmsVaultPermissions() {
        return cmsVaultPermissionRepository.findAll();
    }

    @Transactional(value = "transactionManager")
    public void saveCmsVaultPermission(CmsVaultPermission cmsVaultPermission) {
        cmsVaultPermissionRepository.save(cmsVaultPermission);
    }

    @Transactional(value = "transactionManager")
    public Optional<CmsVaultPermission> getCmsVaultPermissionById(String id) {
        return cmsVaultPermissionRepository.findById(id);
    }

    @Transactional(value = "transactionManager")
    public Optional<CmsVaultPermission> getCmsVaultPermissionByPoNumber(String id) {
        return cmsVaultPermissionRepository.getCmsVaultPermissionById(id);
    }

    @Transactional(value = "transactionManager")
    public List<CmsVaultPermission> getCmsVaultPermissionByUserId(String userId) {
        return cmsVaultPermissionRepository.findCmsVaultPermissionsByUserid(userId);
    }


    @Transactional(value = "transactionManager")
    public void deleteCmsVaultPermission(String id) {
        cmsVaultPermissionRepository.deleteById(id);
    }

    @Transactional(value = "transactionManager")
    public String getCmsVaultPermissionByUserIdAndVaultType(String userId, String vault_acc_type) {

        log.info("userid and vaultType : {}", vault_acc_type );
        Optional<CmsVaultPermission> cmsVaultPermission = cmsVaultPermissionRepository.findByUseridAndVaultAccType(userId, vault_acc_type);
        if (cmsVaultPermission.isPresent()){

            CmsVaultPermission cmsVaultPermission1 =  cmsVaultPermission.get();

            Vault vault = vaultService.getVault(Integer.valueOf(cmsVaultPermission1.getVault_acc_code()));

            return  vault.getAccount();

        } else {
            return null;
        }
    }

    @Transactional(value = "transactionManager")
    public String getCmsVaultPermissionByVaultType(String vault_acc_type) {

        log.info("userid and vaultType : {}", vault_acc_type );
        Optional<CmsVaultPermission> cmsVaultPermission = cmsVaultPermissionRepository.findByVaultAccType(vault_acc_type);
        if (cmsVaultPermission.isPresent()){

            CmsVaultPermission cmsVaultPermission1 =  cmsVaultPermission.get();

            Vault vault = vaultService.getVault(Integer.valueOf(cmsVaultPermission1.getVault_acc_code()));

            return  vault.getAccount();

        } else {
            return null;
        }
    }

//    public List<CmsVaultPermission> getCmsVaultPermissionByVaultAccCode(String vaultAccCode) {
//        return cmsVaultPermissionRepository.getCmsVaultPermissionsByVault_acc_code(vaultAccCode);
//    }
}
