package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.OwnershipDetails;
import com.untucapital.usuite.utg.model.SourceOfFunds;
import com.untucapital.usuite.utg.repository.OwnerShipDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class OwnershipDetailsService {
    @Autowired
    OwnerShipDetailsRepository ownerShipDetailsRepository;

    public void saveOwenershipDetails(OwnershipDetails ownershipDetails){
        ownerShipDetailsRepository.save(ownershipDetails);
    }

    public List<OwnershipDetails> getOwnershipDetailsByLoanId(String loanId){
        return ownerShipDetailsRepository.findByLoanId(loanId);
    }

    public void deleteOwnershipDetails(String id){
        ownerShipDetailsRepository.deleteById(id);
    }

}
