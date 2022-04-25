package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.InvestmentInfow;
import com.untucapital.usuite.utg.model.InvestmentOutflow;
import com.untucapital.usuite.utg.repository.InvestmentOutflowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class InvestmentOutflowService {

    @Autowired
    InvestmentOutflowRepository investmentOutflowRepository;

    public void add(InvestmentOutflow investmentOutflow){
        investmentOutflowRepository.save(investmentOutflow);
    }

    public void delete(String id){
        investmentOutflowRepository.deleteById(id);
    }

    public List<InvestmentOutflow> get(String loanId){
        return investmentOutflowRepository.findInvestmentOutflowByLoanId(loanId);
    }
}
