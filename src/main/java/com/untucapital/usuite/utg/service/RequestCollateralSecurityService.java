package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.CollateralSecurity;
import com.untucapital.usuite.utg.model.RequestCollateralSecurity;
import com.untucapital.usuite.utg.repository.CollateraiSecurityRepository;
import com.untucapital.usuite.utg.repository.RequestCollateraiSecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class RequestCollateralSecurityService {

    @Autowired
    RequestCollateraiSecurityRepository requestCollateraiSecurityRepository;

    public void saveCollateralSecurity(String phoneNumber, String loanAccount, String clientName){
        RequestCollateralSecurity requestCollateralSecurity = new RequestCollateralSecurity();
        requestCollateralSecurity.setPhoneNumber(phoneNumber);
        requestCollateralSecurity.setLoanAcc(loanAccount);
        requestCollateralSecurity.setClientName(clientName);
        requestCollateralSecurity.setCollateralDescription(clientName + " has requested Collateral Securities for Loan Account: " + loanAccount + "\n\nKindly attend");
        System.out.println(requestCollateralSecurity);
        requestCollateraiSecurityRepository.save(requestCollateralSecurity);
    }


}
