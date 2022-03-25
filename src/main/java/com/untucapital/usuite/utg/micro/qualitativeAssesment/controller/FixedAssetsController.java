package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.model.FixedAssets;
import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.FixedAssetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "fixedAssets")
public class FixedAssetsController {
    @Autowired
    private final FixedAssetsService fixedAssetsService;

    public FixedAssetsController(FixedAssetsService fixedAssetsService) {
        this.fixedAssetsService = fixedAssetsService;
    }

    //Add
    @PostMapping("/save")
    public void save(@RequestBody FixedAssets fixedAssets){
        fixedAssetsService.save(fixedAssets);
    }
    //Delete by id
    @DeleteMapping("/delete/{fixedAssetsId}")
    public void deleteById(@PathVariable("fixedAssetsId") String id) {
        fixedAssetsService.deleteById(id);
    }
    //Find all by loan id
    @GetMapping("get/{loanId}")
    public List<FixedAssets> findAllByLoanId(@PathVariable("loanId") String id){
        return fixedAssetsService.findAllByLoanId(id);
    }
}
