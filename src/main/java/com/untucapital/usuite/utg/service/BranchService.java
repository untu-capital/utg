package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.Branches;
import com.untucapital.usuite.utg.model.Business;
import com.untucapital.usuite.utg.model.ClientLoan;
import com.untucapital.usuite.utg.model.User;
import com.untucapital.usuite.utg.repository.BranchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class BranchService extends AbstractService<Branches> {

    private static final Logger log = LoggerFactory.getLogger(BranchService.class);

    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }
    public void saveBranches(Branches branches) {
        branchRepository.save(branches);
    }

    @Override
    protected JpaRepository<Branches, String> getRepository() {
        return branchRepository;
    }

    @Override
    public List<User> getUserByRole(String name) {
        return null;
    }

    public void deleteBranch(String id) {
        branchRepository.deleteById(id);
    }


    }




