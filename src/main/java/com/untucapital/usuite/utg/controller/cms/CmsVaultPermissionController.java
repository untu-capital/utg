package com.untucapital.usuite.utg.controller.cms;

import com.untucapital.usuite.utg.model.cms.CmsVaultPermission;
import com.untucapital.usuite.utg.service.cms.CmsVaultPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("cms/cms_vault_permission")
public class CmsVaultPermissionController {

    private static final Logger log = LoggerFactory.getLogger(CmsVaultPermissionController.class);
    @Autowired
    CmsVaultPermissionService cmsVaultPermissionService;

    @GetMapping
    public List<CmsVaultPermission> list() {
        return cmsVaultPermissionService.getAllCmsVaultPermissions();
    }

    @PostMapping
    public void saveCmsVaultPermission(@RequestBody CmsVaultPermission cmsVaultPermission) {
        log.info(String.valueOf(cmsVaultPermission));
        cmsVaultPermissionService.saveCmsVaultPermission(cmsVaultPermission);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        cmsVaultPermissionService.deleteCmsVaultPermission(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CmsVaultPermission> getCmsVaultPermissionById(@PathVariable("id") String id) {
        Optional<CmsVaultPermission> cmsVaultPermission = cmsVaultPermissionService.getCmsVaultPermissionById(id);

        if (cmsVaultPermission.isPresent()) {
            return new ResponseEntity<>(cmsVaultPermission.get(), HttpStatus.OK);
        } else {
            // Handle the case when the CmsVaultPermissions object is not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @GetMapping("/getByVaultAccCode/{vaultAccCode}")
//    public ResponseEntity<List<CmsVaultPermission>> getCmsVaultPermissionByVaultAccCode(@PathVariable("vaultAccCode") String vaultAccCode) {
//        List<CmsVaultPermission> cmsVaultPermissions = cmsVaultPermissionService.getCmsVaultPermissionByVaultAccCode(vaultAccCode);
//
//        if (!cmsVaultPermissions.isEmpty()) {
//            return new ResponseEntity<>(cmsVaultPermissions, HttpStatus.OK);
//        } else {
//            // Handle the case when no CmsVaultPermission objects are found for the provided vault_acc_code
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }


//    @GetMapping("getByPoNumber/{poNumber}")
//    public ResponseEntity<CmsVaultPermission> getCmsVaultPermissionByPoNumber(@PathVariable("poNumber") String poNumber) {
//        Optional<CmsVaultPermission> cmsVaultPermission = cmsVaultPermissionService.getCmsVaultPermissionByPoNumber(poNumber);
//
//        if (cmsVaultPermission.isPresent()) {
//            return new ResponseEntity<>(cmsVaultPermission.get(), HttpStatus.OK);
//        } else {
//            // Handle the case when no CmsVaultPermissions objects are found
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
}
