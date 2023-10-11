package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.model.DailySalesMicro;
import com.untucapital.usuite.utg.micro.qualitativeAssesment.repo.DailySalesMicroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class DailySalesMicroService {
    @Autowired
    private final DailySalesMicroRepository dailySalesMicroRepository;

    public DailySalesMicroService(DailySalesMicroRepository dailySalesMicroRepository) {
        this.dailySalesMicroRepository = dailySalesMicroRepository;
    }
    //Add
    public void save(DailySalesMicro dailySalesMicro){
        dailySalesMicroRepository.save(dailySalesMicro);
    }
    //Delete by
    public void deleteById(String id){
        dailySalesMicroRepository.deleteById(id);
    }
    //find all by loan id
    public List<DailySalesMicro> findAllByLoanId(String id){
        return dailySalesMicroRepository.findAllByLoanId(id);
    }
}
