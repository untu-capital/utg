package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.model.Liabilities;
import com.untucapital.usuite.utg.micro.qualitativeAssesment.repository.LiabilitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LiabilitiesService {
    @Autowired
    private final LiabilitiesRepository liabilitiesRepository;

    public LiabilitiesService(LiabilitiesRepository liabilitiesRepository) {
        this.liabilitiesRepository = liabilitiesRepository;
    }
    //Add
    public void save(Liabilities liabilities){
        liabilitiesRepository.save(liabilities);
    }
    //Delete by loan id2
    public void deleteById(String id){
        liabilitiesRepository.deleteById(id);
    }
    //Find All by Loan id
    public List<Liabilities> findAllByLoanId(String id){
        return liabilitiesRepository.findAllByLoanId(id);
    }
}
