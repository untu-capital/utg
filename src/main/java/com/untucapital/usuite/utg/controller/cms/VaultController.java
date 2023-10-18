package com.untucapital.usuite.utg.controller.cms;

import com.untucapital.usuite.utg.DTO.cms.UpdateVaultRequest;
import com.untucapital.usuite.utg.DTO.cms.VaultRequest;
import com.untucapital.usuite.utg.model.cms.Vault;
import com.untucapital.usuite.utg.service.cms.VaultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author tjchidanika
 * @created 27/9/2023
 */

@RestController
@RequestMapping("cms/vault")
@RequiredArgsConstructor
public class VaultController {
    private final VaultService vaultService;
    //Add
    @PostMapping("/save")
    public ResponseEntity<Vault> addVault(@RequestBody VaultRequest vaultRequest) {
        return ResponseEntity.ok(vaultService.addVault(vaultRequest));
    }

    @PutMapping("/update")
    //Update
    public ResponseEntity<Vault> updateVault(@RequestBody UpdateVaultRequest vault) {
        System.out.println("Updated Vault Request"+vault.getAccount());
        return ResponseEntity.ok(vaultService.updateVault(vault));
    }

    @DeleteMapping("/delete/{vaultId}")
    //Delete
    public ResponseEntity<String> deleteVault(@PathVariable Integer vaultId) {
        return ResponseEntity.ok(vaultService.deleteVault(vaultId));
    }

    @GetMapping("/get/{vaultId}")
    //Get
    public ResponseEntity<Vault> getVault(@PathVariable Integer vaultId) {
        return ResponseEntity.ok(vaultService.getVault(vaultId));
    }

    @GetMapping("/get/all")
    //Get All
    public ResponseEntity<List<Vault>> getAllVaults() {
        return ResponseEntity.ok(vaultService.getAllVaults());
    }

    @PutMapping("/update/amount/{vaultId}/{amount}")
    //Update Vault Amount
    public ResponseEntity<Vault> updateVaultAmount(@PathVariable Integer vaultId, @PathVariable BigDecimal amount) {
        return ResponseEntity.ok(vaultService.updateVaultAmount(vaultId, amount));
    }

    @PutMapping("/update/max-amount/{vaultId}/{amount}")
    //Update Maximum Amount
    public ResponseEntity<Vault> updateMaxAmount(@PathVariable Integer vaultId, @PathVariable BigDecimal amount) {
        return ResponseEntity.ok(vaultService.updateVaultMaxAmount(vaultId, amount));
    }

    //Get Vaults By Branch Id
    @GetMapping("/get/all-by-branch/{branchId}")
    public ResponseEntity<List<Vault>> getAllVaultsByBranch(@PathVariable String branchId) {
        return ResponseEntity.ok(vaultService.getAllVaultsByBranch(branchId));
    }
}