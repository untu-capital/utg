package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.model.MainProductPurchasesMicro;
import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.MainProductPurchasesMicroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("mainProductPurchases")
public class MainProductPurchasesMicroController {
    @Autowired
    private final MainProductPurchasesMicroService  mainProductPurchasesMicroService;

    public MainProductPurchasesMicroController(MainProductPurchasesMicroService mainProductPurchasesMicroService) {
        this.mainProductPurchasesMicroService = mainProductPurchasesMicroService;
    }
    //Add
    public void save(@RequestBody MainProductPurchasesMicro mainProductPurchasesMicro){
        mainProductPurchasesMicroService.save(mainProductPurchasesMicro);
    }
    //Delete
    public void deleteById(@PathVariable("mainProductPurchasesId") String id){
        mainProductPurchasesMicroService.deleteById(id);
    }
    //Find all by loan
    public List<MainProductPurchasesMicro> findAllByLoanId(@PathVariable("loanId") String id){
        return mainProductPurchasesMicroService.findAllLoanId(id);
    }
}
