package com.untucapital.usuite.utg.service.cms;

import com.untucapital.usuite.utg.DTO.cms.UpdateVaultRequest;
import com.untucapital.usuite.utg.DTO.cms.VaultRequest;
import com.untucapital.usuite.utg.exception.ResourceNotFoundRequestException;
import com.untucapital.usuite.utg.model.cms.Vault;
import com.untucapital.usuite.utg.repository.cms.VaultRepository;
import com.untucapital.usuite.utg.model.Branches;
import com.untucapital.usuite.utg.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author tjchidanika
 * @created 27/9/2023
 */

@Service
@RequiredArgsConstructor
@Transactional
public class VaultService {
    private final VaultRepository vaultRepository;
    private final BranchRepository branchRepository;
    //Add
    public Vault addVault(VaultRequest vaultRequest) {

        Branches branch = branchRepository.findById(vaultRequest.getBranchId())
                .orElseThrow(() -> new RuntimeException("Branch not found"));
        Vault vault = Vault.builder()
                .account(vaultRequest.getAccount())
                .type(vaultRequest.getType())
                .name(vaultRequest.getName())
                .branch(branch)
                .build();
        return vaultRepository.save(vault);
    }

    //Update
    @Transactional
    public Vault updateVault(UpdateVaultRequest vault) {

        Vault existingVault = vaultRepository.findById(vault.getId())
                .orElseThrow(() -> new RuntimeException("Vault not found"));


        if (vault.getAccount() != null && !vault.getAccount().equals(existingVault.getAccount())) {
            existingVault.setAccount(vault.getAccount());
        }

        if (vault.getName() != null && !vault.getName().equals(existingVault.getName())) {
            existingVault.setName(vault.getName());
        }

        if (vault.getType() != null && !vault.getType().equals(existingVault.getType())) {
            existingVault.setType(vault.getType());
        }

        if (vault.getBranchId() != null && !vault.getBranchId().equals(existingVault.getBranch().getId())) {
            Branches branch = branchRepository.findById(vault.getBranchId())
                    .orElseThrow(() -> new RuntimeException("Branch not found"));
            existingVault.setBranch(branch);
        }

        return vaultRepository.save(existingVault);
    }
    //Delete
    public String deleteVault(Integer vaultId) {

        Vault existingVault = vaultRepository.findById(vaultId)
                .orElseThrow(() -> new RuntimeException("Vault not found"));

        vaultRepository.delete(existingVault);

        return String.format("Vault with id %d deleted successfully", vaultId);
    }
    //Get
    public Vault getVault(Integer vaultId) {
        return vaultRepository.findById(vaultId)
                .orElseThrow(() -> new ResourceNotFoundRequestException("Vault", "id", vaultId));
    }

    //Get All
    public List<Vault> getAllVaults() {
        return vaultRepository.findAll();
    }

    //Update Vault Amount
    public Vault updateVaultAmount(Integer vaultId, BigDecimal amount) {

        Vault existingVault = vaultRepository.findById(vaultId)
                .orElseThrow(() -> new RuntimeException("Vault not found"));

        existingVault.setCurrentAmount(existingVault.getCurrentAmount().add(amount));

        return vaultRepository.save(existingVault);
    }

    //Update Maximum Amount
    public Vault updateVaultMaxAmount(Integer vaultId, BigDecimal amount) {

        Vault existingVault = vaultRepository.findById(vaultId)
                .orElseThrow(() -> new RuntimeException("Vault not found"));

        existingVault.setMaxAmount(existingVault.getMaxAmount().add(amount));

        return vaultRepository.save(existingVault);
    }

    //Vaults By Branch
    public List<Vault> getAllVaultsByBranch(String branchId) {
        Branches branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new RuntimeException("Branch not found"));
        return vaultRepository.findByBranch(branch);
    }
}
