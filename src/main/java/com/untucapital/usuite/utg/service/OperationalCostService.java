package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.DirectCost;
import com.untucapital.usuite.utg.model.OperationalCost;
import com.untucapital.usuite.utg.repository.OperationalCostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class OperationalCostService {
    @Autowired
    private OperationalCostRepository operationalCostRepository;

    public void  saveDirectCost(OperationalCost operationalCost) {
        operationalCostRepository.save(operationalCost);
    }

    public List<OperationalCost> getAllDirectCosts() {
        return operationalCostRepository.findAll();
    }

    public List<OperationalCost> getCostsByLoanId(String loanId) {
        return operationalCostRepository.findbyLoanId(loanId);
    }

    public void deleteOperationalCost(String id) {
        operationalCostRepository.deleteById(id);
    }

}
