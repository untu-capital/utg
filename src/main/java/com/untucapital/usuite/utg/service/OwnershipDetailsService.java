package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.OwnershipDetails;
import com.untucapital.usuite.utg.repository.OwnerShipDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@javax.transaction.Transactional
public class OwnershipDetailsService {
    @Autowired
    OwnerShipDetailsRepository ownerShipDetailsRepository;

    @Transactional(value = "transactionManager")
    public void saveOwenershipDetails(OwnershipDetails ownershipDetails){
        ownerShipDetailsRepository.save(ownershipDetails);
    }

    @Transactional(value = "transactionManager")
    public List<OwnershipDetails> getOwnershipDetailsByLoanId(String loanId){
        return ownerShipDetailsRepository.findByLoanId(loanId);
    }

    @Transactional(value = "transactionManager")
    public void deleteOwnershipDetails(String id){
        ownerShipDetailsRepository.deleteById(id);
    }

}
