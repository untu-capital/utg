package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.Branches;
import com.untucapital.usuite.utg.repository.BranchRepository;
import com.untucapital.usuite.utg.service.AbstractService;
import com.untucapital.usuite.utg.service.BranchService;
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
    private static final Logger log = LoggerFactory.getLogger(BranchController.class);
    private final BranchService branchService;
    @Autowired
    BranchRepository branchRepository;

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

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatebranches(@PathVariable String id, @RequestBody Branches branches) {
        Branches updatebranches = branchRepository.findBranchesById(id);

        if (updatebranches != null) {
            updatebranches.setBranchName(branches.getBranchName());
            updatebranches.setBranchAddress(branches.getBranchAddress());
            updatebranches.setBranchStatus(branches.getBranchStatus());
            updatebranches.setBranchTellPhone(branches.getBranchTellPhone());
            updatebranches.setCode(branches.getCode());
            updatebranches.setVaultAccountNumber(branches.getVaultAccountNumber());
            updatebranches.setBranchCode(branches.getBranchCode());

            branchRepository.save(updatebranches);
            return new ResponseEntity<>("Branch successfully updated.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Branch not found.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllBranches")
    public List<Branches> getAllBranches() {
        return branchService.getAllBranches();
    }


    @Override
    protected AbstractService<Branches> getService() {
        return branchService;
    }
}
