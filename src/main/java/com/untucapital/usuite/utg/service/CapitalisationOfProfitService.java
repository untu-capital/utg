package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.CapitalisationOfProfit;
import com.untucapital.usuite.utg.repository.CapitalisationOfProfitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class CapitalisationOfProfitService {
    @Autowired
    CapitalisationOfProfitRepository capitalisationOfProfitRepository;

    public void add(CapitalisationOfProfit capitalisationOfProfit){
        capitalisationOfProfitRepository.save(capitalisationOfProfit);
    }

    public void delete(String id){
        capitalisationOfProfitRepository.deleteById(id);
    }

    public List<CapitalisationOfProfit> get(String loanId){
        return capitalisationOfProfitRepository.findCapitalisationOfProfitsByLoanId(loanId);
    }
}
