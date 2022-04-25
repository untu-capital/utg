package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.MostImportantClients;
import com.untucapital.usuite.utg.model.OwnershipDetails;
import com.untucapital.usuite.utg.repository.MostImportantClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class MostImportantClientsService {
    @Autowired
    MostImportantClientsRepository mostImportantClientsRepository;

    public void saveMostImportantClients(MostImportantClients mostImportantClients){
        mostImportantClientsRepository.save(mostImportantClients);
    }

    public List<MostImportantClients> getMostImportantClientsByLoanId(String loanId){
        return mostImportantClientsRepository.findByLoanId(loanId);
    }

    public void deleteMostImportantCliennts(String id){
        mostImportantClientsRepository.deleteById(id);
    }
}

