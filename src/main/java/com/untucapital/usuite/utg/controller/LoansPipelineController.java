package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.LoanOfficerProductivityDTO;
import com.untucapital.usuite.utg.dto.LoansPipelineDTO;
import com.untucapital.usuite.utg.dto.request.LoansPipelineRequestDTO;
import com.untucapital.usuite.utg.dto.response.LoansPipelineResponseDTO;
import com.untucapital.usuite.utg.service.LoansPipelineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "credit_application_pipeline")
public class LoansPipelineController {

    private final LoansPipelineService loansPipelineService;


    @PostMapping("/loans")
    public ResponseEntity<LoansPipelineResponseDTO> createLoan(@RequestBody LoansPipelineRequestDTO loansPipeline) {
        log.info("request: {}", loansPipeline);
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

//    @GetMapping("/getLoPipeline/{userId}")
//    public List<LoansPipeline> getLoPipeline(@PathVariable("userId") String userId) {
//        return loansPipelineService.getLoPipeline(userId);
//
//    }

    @GetMapping("/getLoPipeline/{userId}")
    public ResponseEntity<List<LoansPipelineResponseDTO>> getLoPipeline(@PathVariable("userId") String userId) {
        List<LoansPipelineResponseDTO> loans = loansPipelineService.getLoPipeline(userId);
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }

    @GetMapping("/getPipelineSummary")
    public List<LoansPipelineDTO> getLoanPipelineSummary() {
        return loansPipelineService.getLoanPipelineSummary();
    }

    @GetMapping("/getLoanOfficerProductivity")
    public List<LoanOfficerProductivityDTO> getLoanOfficerProductivity() {
        return loansPipelineService.getLoanOfficerProductivity();
    }

}
