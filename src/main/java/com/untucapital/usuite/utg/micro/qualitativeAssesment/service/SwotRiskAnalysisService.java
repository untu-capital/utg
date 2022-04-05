package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.model.SwotRiskAnalysis;
import com.untucapital.usuite.utg.micro.qualitativeAssesment.repository.SwotRiskAnalysisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SwotRiskAnalysisService {
    @Autowired
    private final SwotRiskAnalysisRepository swotRiskAnalysisRepository;

    public SwotRiskAnalysisService(SwotRiskAnalysisRepository swotRiskAnalysisRepository) {
        this.swotRiskAnalysisRepository = swotRiskAnalysisRepository;
    }
    //Add
    public void save(SwotRiskAnalysis swotRiskAnalysis){
        swotRiskAnalysisRepository.save(swotRiskAnalysis);
    }
    //Delete By Id
    public void deleteById(String id){
        swotRiskAnalysisRepository.deleteById(id);
    }
    //Find All By Loan Id
    public List<SwotRiskAnalysis> findAllByLoanId(String id){
        return swotRiskAnalysisRepository.findAllByLoanId(id);
    }
}
