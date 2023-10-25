package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.MostImportantClients;
import com.untucapital.usuite.utg.model.OwnershipDetails;
import com.untucapital.usuite.utg.repository.MostImportantClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@javax.transaction.Transactional
public class MostImportantClientsService {
    @Autowired
    MostImportantClientsRepository mostImportantClientsRepository;

    @Transactional(value = "transactionManager")
    public void saveMostImportantClients(MostImportantClients mostImportantClients){
        mostImportantClientsRepository.save(mostImportantClients);
    }

    @Transactional(value = "transactionManager")
    public List<MostImportantClients> getMostImportantClientsByLoanId(String loanId){
        return mostImportantClientsRepository.findByLoanId(loanId);
    }

    @Transactional(value = "transactionManager")
    public void deleteMostImportantCliennts(String id){
        mostImportantClientsRepository.deleteById(id);
    }
}

