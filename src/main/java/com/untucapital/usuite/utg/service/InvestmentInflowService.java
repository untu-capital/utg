package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.InvestmentInfow;
import com.untucapital.usuite.utg.repository.InvestmentInfowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class InvestmentInflowService {
    @Autowired
    InvestmentInfowRepository investmentInfowRepository;

    public List<InvestmentInfow> findByLoanId(String loanId){
        return investmentInfowRepository.findInvestmentInfowByLoanId(loanId);
    }

    public void delete(String id){
        investmentInfowRepository.deleteById(id);
    }

    public void add(InvestmentInfow investmentInfow){
        investmentInfowRepository.save(investmentInfow);
    }
}
