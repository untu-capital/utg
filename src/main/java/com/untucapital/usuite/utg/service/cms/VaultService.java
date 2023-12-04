package com.untucapital.usuite.utg.service.cms;


import com.untucapital.usuite.utg.dto.UpdateVaultRequest;
import com.untucapital.usuite.utg.dto.VaultRequest;
import com.untucapital.usuite.utg.model.Branches;
import com.untucapital.usuite.utg.model.cms.Vault;
import com.untucapital.usuite.utg.repository.BranchRepository;
import com.untucapital.usuite.utg.repository.cms.VaultRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * @author tjchidanika
 * @created 27/9/2023
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class VaultService {

    private final VaultRepository vaultRepository;
    private final BranchRepository branchRepository;
    //Add
    @Transactional(value = "transactionManager")
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
    @Transactional(value = "transactionManager")
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
    @Transactional(value = "transactionManager")
    public String deleteVault(Integer vaultId) {

        Vault existingVault = vaultRepository.findById(vaultId)
                .orElseThrow(() -> new RuntimeException("Vault not found"));
        vaultRepository.delete(existingVault);

        return String.format("Vault with id %d deleted successfully", vaultId);
    }
    //Get
    @Transactional(value = "transactionManager")
    public Vault getVault(Integer vaultId) {
        return vaultRepository.findById(vaultId)
                .orElseThrow(() -> new RuntimeException("Vault not found"));
    }
    //Get All
    @Transactional(value = "transactionManager")
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
    @Transactional(value = "transactionManager")
    public Vault updateVaultMaxAmount(Integer vaultId, BigDecimal amount) {

        Vault existingVault = vaultRepository.findById(vaultId)
                .orElseThrow(() -> new RuntimeException("Vault not found"));

        existingVault.setMaxAmount(amount);

        return vaultRepository.save(existingVault);
    }

    @Transactional(value = "transactionManager")
    public Vault getVaultByBranchAndType(String branch, String type) {
        log.info("Branch and Type:{}", branch + type);
        Optional<Vault> vault =vaultRepository.findVaultByBranch_BranchNameAndType(branch, type);

        //FIXME: this is a bypass for testing
        if(vault.isEmpty()) {
            Vault vault1 = new Vault();
            vault1.setAccount("8422/000/HRE/FCA/MV");
            return vault1;
        }

        return vault.get();
    }

    @Transactional(value = "transactionManager")
    public String getVaultByType(String type) {
        log.info("Type:{}", type);
        Optional<Vault> vault =vaultRepository.findByType(type);
        if (vault.isPresent()){
            Vault vault1 = vault.get();
            return vault1.getAccount();
        } else {
            return null;
        }
    }

    @Transactional(value = "transactionManager")
    public List<Vault> getVaultsByBranch(String branch) {
        return vaultRepository.findVaultByBranch_BranchName(branch);
    }
    //Vaults By Branch
    public List<Vault> getAllVaultsByBranch(String branchId) {
        Branches branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new RuntimeException("Branch not found"));
        return vaultRepository.findByBranch(branch);
    }
}
