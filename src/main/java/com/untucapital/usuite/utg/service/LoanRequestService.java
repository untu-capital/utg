package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.LoanRequest;
import com.untucapital.usuite.utg.repository.LoanRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class LoanRequestService {
    @Autowired
    LoanRequestRepository loanRequestRepository;

    public void saveLoanRequest(LoanRequest loanRequest){
        loanRequestRepository.save(loanRequest);
    }

    public LoanRequest getByLoanId(String loanId){
        return loanRequestRepository.findByLoanId(loanId);
    }
    public void deleteLoanRequest(String id){
        loanRequestRepository.deleteById(id);
    }
}
