package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.FixedAsset;
import com.untucapital.usuite.utg.service.FixedAssetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("fixedAssets")
public class FixedAssetsController {
    @Autowired
    FixedAssetService fixedAssetService;

    private static final Logger log = LoggerFactory.getLogger(FixedAssetsController.class);

    @GetMapping("/get/{loanId}")
    public List<FixedAsset> getAllByLoanId(@PathVariable("loanId") String loanId) {
        return fixedAssetService.getFixedAssets(loanId);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable String id){
            fixedAssetService.deleteFixedAsset(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody FixedAsset fixedAsset) {
        fixedAssetService.saveFixedAssets(fixedAsset);
    }

}
