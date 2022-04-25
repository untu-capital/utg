package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.model.FixedAssetsMicro;
import com.untucapital.usuite.utg.micro.qualitativeAssesment.repository.FixedAssetsMicroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FixedAssetsServiceMicro {
    @Autowired
    private final FixedAssetsMicroRepository fixedAssetsRepository;

    public FixedAssetsServiceMicro(FixedAssetsMicroRepository fixedAssetsRepository) {
        this.fixedAssetsRepository = fixedAssetsRepository;
    }
    //Add
    public void save(FixedAssetsMicro fixedAssets){
        fixedAssetsRepository.save(fixedAssets);
    }
    //Delete by id
    public void deleteById(String id){
        fixedAssetsRepository.deleteById(id);
    }
    //Find All By loan id
    public List<FixedAssetsMicro> findAllByLoanId(String id){
        return fixedAssetsRepository.findFixedAssetsMicroByLoanId(id);
    }
}
