package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.model.CurrentAssetsMicro;
import com.untucapital.usuite.utg.micro.qualitativeAssesment.repository.CurrentAssetsMicroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrentAssetsMicroService {
    @Autowired
    private final CurrentAssetsMicroRepository currentAssetsMicroRepository;

    public CurrentAssetsMicroService(CurrentAssetsMicroRepository currentAssetsMicroRepository) {
        this.currentAssetsMicroRepository = currentAssetsMicroRepository;
    }

    public void save(CurrentAssetsMicro currentAssetsMicro) {
        currentAssetsMicroRepository.save(currentAssetsMicro);
    }

    public void deleteById(String id) {
        currentAssetsMicroRepository.deleteById(id);
    }

    public List<CurrentAssetsMicro> findAllByLoanId(String id) {
        return currentAssetsMicroRepository.findAllByLoanId(id);
    }
}
