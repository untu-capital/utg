package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.DirectCost;
import com.untucapital.usuite.utg.repository.DirectCostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectCostService{

    @Autowired
    private final DirectCostRepository directCostRepository;

    public DirectCostService(DirectCostRepository directCostRepository) {
        this.directCostRepository = directCostRepository;
    }

    //Add New Direct Cost
    public void addDirectCost(DirectCost directCost){
        directCostRepository.save(directCost);
    }

    //Find all Direct Costs by Loan id and Sort CreatedAt
    public List<DirectCost> allDirectCost(String id){
        return directCostRepository.findDirectCostByLoanId(id);
    }

    //Delete Direct Cost By id
    public void deleteDirectCost(String id){
        directCostRepository.deleteById(id);
    }
}
