package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.Branches;
import com.untucapital.usuite.utg.model.User;
import com.untucapital.usuite.utg.repository.BranchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@javax.transaction.Transactional
@Service
public class BranchService extends AbstractService<Branches> {

    private static final Logger log = LoggerFactory.getLogger(BranchService.class);

    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Transactional(value = "transactionManager")
    public void saveBranches(Branches branches) {
        branchRepository.save(branches);
    }

    @Override
    @Transactional(value = "transactionManager")
    public JpaRepository<Branches, String> getRepository() {
        return branchRepository;
    }

    @Override
    @Transactional(value = "transactionManager")
    public List<User> getUserByRole(String name) {
        return null;
    }

    @Transactional(value = "transactionManager")
    public void deleteBranch(String id) {
        branchRepository.deleteById(id);
    }

    @Transactional(value = "transactionManager")
    public List<Branches> getAllBranches() {
        return branchRepository.findAll();
    }

    @Transactional(value = "transactionManager")
    public Branches getBranchesById(String id) {
        return branchRepository.findBranchesById(id);
    }

    @Transactional(value = "transactionManager")
    public Branches getBranchByName(String name) {
        return branchRepository.findBranchesByBranchName(name);
    }
}
