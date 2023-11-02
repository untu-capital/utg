package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.DTO.request.LoansPipelineRequestDTO;
import com.untucapital.usuite.utg.DTO.response.LoansPipelineResponseDTO;
import com.untucapital.usuite.utg.model.LoansPipeline;
import com.untucapital.usuite.utg.repository.LoansPipelineRepository;
import com.untucapital.usuite.utg.service.LoansPipelineService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "credit_application_pipeline")
public class LoansPipelineController {

    private final LoansPipelineService loansPipelineService;


    @PostMapping("/loans")
    public ResponseEntity<LoansPipelineResponseDTO> createLoan(@RequestBody LoansPipelineRequestDTO loansPipeline) {
        LoansPipelineResponseDTO savedLoan = loansPipelineService.createLoan(loansPipeline);
        return new ResponseEntity<>(savedLoan, HttpStatus.CREATED);
    }

    @GetMapping("/loans")
    public ResponseEntity<List<LoansPipelineResponseDTO>> getAllLoans() {
        List<LoansPipelineResponseDTO> loans = loansPipelineService.getAllLoans();
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }

    @GetMapping("/loans/count/{loanOfficer}")
    public ResponseEntity<Integer> getCountByLoanOfficer(@PathVariable("loanOfficer") String loanOfficer) {
        Integer count = loansPipelineService.getCountByLoanOfficer(loanOfficer);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }


}
