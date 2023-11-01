package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.DTO.request.LoansPipelineRequestDTO;
import com.untucapital.usuite.utg.DTO.response.LoansPipelineResponseDTO;
import com.untucapital.usuite.utg.model.LoansPipeline;
import com.untucapital.usuite.utg.repository.LoansPipelineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class LoansPipelineService {

    private final LoansPipelineRepository loansPipelineRepository;


    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public LoansPipelineResponseDTO createLoan(LoansPipelineRequestDTO request) {

        LoansPipeline loansPipeline = new LoansPipeline();
        LoansPipelineResponseDTO response = new LoansPipelineResponseDTO();
        BeanUtils.copyProperties(request, loansPipeline);
        LoansPipeline savedLoan = loansPipelineRepository.save(loansPipeline);
        BeanUtils.copyProperties(savedLoan, response);

        return response;
    }

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public List<LoansPipelineResponseDTO> getAllLoans() {

        List<LoansPipelineResponseDTO> response = new ArrayList<>();
        List<LoansPipeline> loansPipelineList = loansPipelineRepository.findAll();

        for (LoansPipeline loansPipeline : loansPipelineList) {
            LoansPipelineResponseDTO loanResponseDTO = new LoansPipelineResponseDTO();
            BeanUtils.copyProperties(loansPipeline, loanResponseDTO);

            response.add(loanResponseDTO);
        }
        return response;
    }

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public Integer getCountByLoanOfficer(String loanOfficer) {
        int count = loansPipelineRepository.countByLoanOfficerAndLoanStatus(loanOfficer, "Disbursed");
        return count;
    }


}
