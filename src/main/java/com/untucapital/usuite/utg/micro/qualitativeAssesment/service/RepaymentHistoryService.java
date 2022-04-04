package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.model.RepaymentHistory;
import com.untucapital.usuite.utg.micro.qualitativeAssesment.repository.RepaymentHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepaymentHistoryService {
    @Autowired
    private final RepaymentHistoryRepository repaymentHistoryRepository;

    public RepaymentHistoryService(RepaymentHistoryRepository repaymentHistoryRepository) {
        this.repaymentHistoryRepository = repaymentHistoryRepository;
    }
    //Add
    public void save(RepaymentHistory repaymentHistory){
        repaymentHistoryRepository.save(repaymentHistory);
    }
    //Delete by id
    public void deleteBybId(String id){
        repaymentHistoryRepository.deleteById(id);
    }
    //Find All By Id
    public List<RepaymentHistory> findAllByLoanId(String id){
        return repaymentHistoryRepository.findAllByLoanId(id);
    }
}
