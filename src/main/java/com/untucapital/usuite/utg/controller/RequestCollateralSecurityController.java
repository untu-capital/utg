package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.CollateralSecurity;
import com.untucapital.usuite.utg.model.RequestCollateralSecurity;
import com.untucapital.usuite.utg.repository.RequestCollateraiSecurityRepository;
import com.untucapital.usuite.utg.service.CollateralSecurityService;
import com.untucapital.usuite.utg.service.RequestCollateralSecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("requestCollateralSecurity")
public class RequestCollateralSecurityController {

    @Autowired
    RequestCollateralSecurityService requestCollateralSecurityService;

    @Autowired
    RequestCollateraiSecurityRepository requestCollateraiSecurityRepository;

    private static final Logger log = LoggerFactory.getLogger(RequestCollateralSecurityController.class);

//    @GetMapping("/get/{loanId}")
//    public List<CollateralSecurity> getByLoanId(@PathVariable("loanId") String loanId) {
//        return collateralSecurityService.get(loanId);
//    }

    @PostMapping("/addRequestCollateralSecurity/{phoneNumber}/{loanAccount}/{clientName}")
    public void getRequestCollateralSecurity(@PathVariable("phoneNumber") String phoneNumber, @PathVariable("loanAccount") String loanAccount, @PathVariable("clientName") String clientName) {
        requestCollateralSecurityService.saveCollateralSecurity(phoneNumber, loanAccount, clientName);
    }



}
