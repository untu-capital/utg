package com.untucapital.usuite.utg.micro.qualitativeAssesment.model;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.LoanOfficerProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "loanOfficerProposal")
public class LoanOfficerProposalController {
    @Autowired
    private final LoanOfficerProposalService loanOfficerProposalService;

    public LoanOfficerProposalController(LoanOfficerProposalService loanOfficerProposalService) {
        this.loanOfficerProposalService = loanOfficerProposalService;
    }
    //Add
    @PostMapping("/save")
    public void save(@RequestBody LoanOfficerProposal loanOfficerProposal){
        loanOfficerProposalService.save(loanOfficerProposal);
    }
    //Delete by id
    @DeleteMapping("/delete/{loanOfficerProposalId}")
    public void deleteById(@PathVariable("loanOfficerProposalId") String id){
        loanOfficerProposalService.deleteById(id);
    }
    //Find by loan id
    @GetMapping("/get/{loanId}")
    public List<LoanOfficerProposal> findAllLoanById(@PathVariable("loanId") String id){
        return loanOfficerProposalService.findAllByLoanId(id);
    }
}
