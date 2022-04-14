package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.LoanRequest;
import com.untucapital.usuite.utg.repository.LoanRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class LoanRequestService {
    @Autowired
    LoanRequestRepository loanRequestRepository;

    public void saveLoanRequest(LoanRequest loanRequest){
        loanRequestRepository.save(loanRequest);
    }

    public List<LoanRequest> getByLoanId(String loanId){
        return loanRequestRepository.findLoanRequestByLoanId(loanId);
    }
    public void deleteLoanRequest(String id){
        loanRequestRepository.deleteById(id);
    }
}
