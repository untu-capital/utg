package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.model.FixedAssets;
import com.untucapital.usuite.utg.micro.qualitativeAssesment.repository.FixedAssetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FixedAssetsService {
    @Autowired
    private final FixedAssetsRepository fixedAssetsRepository;

    public FixedAssetsService(FixedAssetsRepository fixedAssetsRepository) {
        this.fixedAssetsRepository = fixedAssetsRepository;
    }
    //Add
    public void save(FixedAssets fixedAssets){
        fixedAssetsRepository.save(fixedAssets);
    }
    //Delete by id
    public void deleteById(String id){
        fixedAssetsRepository.deleteById(id);
    }
    //Find All By loan id
    public List<FixedAssets> findAllByLoanId(String id){
        return fixedAssetsRepository.findAllByLoanId(id);
    }
}
