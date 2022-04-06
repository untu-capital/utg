package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.model.PurchaseBehaviourMicro;
import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.PurchaseBehaviourMicroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("purchaseBehaviour")
public class PurchaseBehaviourMicroController {
    @Autowired
    private final PurchaseBehaviourMicroService purchaseBehaviourMicroService;

    public PurchaseBehaviourMicroController(PurchaseBehaviourMicroService purchaseBehaviourMicroService) {
        this.purchaseBehaviourMicroService = purchaseBehaviourMicroService;
    }
    //Add
    public void save(@RequestBody PurchaseBehaviourMicro purchaseBehaviourMicro){
        purchaseBehaviourMicroService.save(purchaseBehaviourMicro);
    }
    //Delete by id
    public void deleteById(@PathVariable("purchaseBehaviourId") String id){
        purchaseBehaviourMicroService.deleteById(id);
    }
    //Find All by loan ID
    public List<PurchaseBehaviourMicro> findAllByLoanId(@PathVariable("loanId") String id){
        return purchaseBehaviourMicroService.findAllByLoanId(id);
    }

}
