package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.model.LoanOfficerProposal;
import com.untucapital.usuite.utg.micro.qualitativeAssesment.repository.LoanOfficerProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanOfficerProposalService {
    @Autowired
    private final LoanOfficerProposalRepository loanOfficerProposalRepository;

    public LoanOfficerProposalService(LoanOfficerProposalRepository loanOfficerProposalRepository) {
        this.loanOfficerProposalRepository = loanOfficerProposalRepository;
    }
    //Add
    public void save(LoanOfficerProposal loanOfficerProposal){
        loanOfficerProposalRepository.save(loanOfficerProposal);
    }
    //Delete by id
    public void deleteById(String id){
        loanOfficerProposalRepository.deleteById(id);
    }
    //Find all by loan id
    public List<LoanOfficerProposal> findAllByLoanId(String id){
        return  loanOfficerProposalRepository.findAllByLoanId(id);
    }
}
