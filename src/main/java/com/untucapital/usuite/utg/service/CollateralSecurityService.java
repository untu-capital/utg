package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.CollateralSecurity;
import com.untucapital.usuite.utg.repository.CollateraiSecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class CollateralSecurityService {
    @Autowired
    CollateraiSecurityRepository collateraiSecurityRepository;

    public List<CollateralSecurity> get(String loanId){
        return collateraiSecurityRepository.findCollateralSecurityByLoanId(loanId);

    }

    public void delete(String id){
        collateraiSecurityRepository.deleteById(id);
    }

    public void add(CollateralSecurity collateralSecurity){
        collateraiSecurityRepository.save(collateralSecurity);
    }
}
