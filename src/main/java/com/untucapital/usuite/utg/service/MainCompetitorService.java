package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.MainCompetitor;
import com.untucapital.usuite.utg.model.MostImportantClients;
import com.untucapital.usuite.utg.repository.MainCompetitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class MainCompetitorService {
    @Autowired
    MainCompetitorRepository mainCompetitorRepository;

    public void saveMainCompetitor(MainCompetitor mainCompetitor){
        mainCompetitorRepository.save(mainCompetitor);
    }

    public List<MainCompetitor> getMainCompetitorByLoanId(String loanId){
        return mainCompetitorRepository.findByLoanId(loanId);
    }

    public void deleteMainCompetitor(String id){
        mainCompetitorRepository.deleteById(id);
    }
}
