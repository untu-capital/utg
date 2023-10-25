package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.CapitalisationOfProfit;
import com.untucapital.usuite.utg.repository.CapitalisationOfProfitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@javax.transaction.Transactional
public class CapitalisationOfProfitService {
    @Autowired
    CapitalisationOfProfitRepository capitalisationOfProfitRepository;

    @Transactional(value = "transactionManager")
    public void add(CapitalisationOfProfit capitalisationOfProfit){
        capitalisationOfProfitRepository.save(capitalisationOfProfit);
    }

    @Transactional(value = "transactionManager")
    public void delete(String id){
        capitalisationOfProfitRepository.deleteById(id);
    }

    @Transactional(value = "transactionManager")
    public List<CapitalisationOfProfit> get(String loanId){
        return capitalisationOfProfitRepository.findCapitalisationOfProfitsByLoanId(loanId);
    }
}
