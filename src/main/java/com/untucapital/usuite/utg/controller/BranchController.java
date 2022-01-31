package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.Branches;
import com.untucapital.usuite.utg.model.Business;
import com.untucapital.usuite.utg.model.Cities;
import com.untucapital.usuite.utg.model.ClientLoan;
import com.untucapital.usuite.utg.repository.BranchRepository;
import com.untucapital.usuite.utg.repository.ClientRepository;
import com.untucapital.usuite.utg.service.AbstractService;
import com.untucapital.usuite.utg.service.BranchService;
import com.untucapital.usuite.utg.service.CitiesService;
import com.untucapital.usuite.utg.service.ClientLoanApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "branches")
public class BranchController extends AbstractController<Branches> {
    @Autowired
    BranchRepository branchRepository;

    private static final Logger log = LoggerFactory.getLogger(BranchController.class);

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    //build save branch REST API
    @PostMapping("/addBranch")
    public void add(@RequestBody Branches branches) {
        branchService.saveBranches(branches);
    }

    @DeleteMapping("/deleteBranch/{id}")
    public void delete(@PathVariable String id) {

        branchService.deleteBranch(id);
    }

    @Override
    protected AbstractService<Branches> getService() {
        return branchService;
    }
}
