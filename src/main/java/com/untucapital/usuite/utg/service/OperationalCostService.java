package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.DirectCost;
import com.untucapital.usuite.utg.model.OperationalCost;
import com.untucapital.usuite.utg.repository.OperationalCostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Service
@javax.transaction.Transactional
public class OperationalCostService {
    @Autowired
    private OperationalCostRepository operationalCostRepository;

    @Transactional(value = "transactionManager")
    public void  saveDirectCost(OperationalCost operationalCost) {
        operationalCostRepository.save(operationalCost);
    }

    @Transactional(value = "transactionManager")
    public List<OperationalCost> getAllDirectCosts() {
        return operationalCostRepository.findAll();
    }

    @Transactional(value = "transactionManager")
    public List<OperationalCost> getCostsByLoanId(String loanId) {
        return operationalCostRepository.findbyLoanId(loanId);
    }

    @Transactional(value = "transactionManager")
    public void deleteOperationalCost(String id) {
        operationalCostRepository.deleteById(id);
    }

}
