package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.DirectCost;
import com.untucapital.usuite.utg.repository.DirectCostRepository;
import com.untucapital.usuite.utg.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class DirectCostService {

    @Autowired
    private DirectCostRepository directCostRepository;

    public void  saveDirectCost(DirectCost directCost) {
        directCostRepository.save(directCost);
    }

    public List<DirectCost> getAllDirectCosts() {
        return directCostRepository.findAll();
    }


    public List<DirectCost> getDirectCostsByLoanId(String loanId) {
        return directCostRepository.findbyLoanId(loanId);
    }

    public void deleteDirectCost(String id) {
        directCostRepository.deleteById(id);
    }
}
