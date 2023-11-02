package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.po.Requisitions;
import com.untucapital.usuite.utg.repository.RequisitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@javax.transaction.Transactional
public class RequisitionService {

    @Autowired
    private RequisitionRepository requisitionRepository;

    @Transactional(value = "transactionManager")
    public List<Requisitions> getAllRequistions() {
        return requisitionRepository.findAll();
    }

    @Transactional(value = "transactionManager")
    public void saveRequisition(Requisitions requisitions) {
        requisitionRepository.save(requisitions);
    }

    @Transactional(value = "transactionManager")
    public Optional<Requisitions> getRequisitionById(String id) {
        return requisitionRepository.findById(id);
    }

    @Transactional(value = "transactionManager")
    public Optional<Requisitions> getRequisitionByPoNumber(String poNumber) {
        return requisitionRepository.getRequisitionsByPoNumber(poNumber);
    }

    @Transactional(value = "transactionManager")
    public void deleteRequisition(String id) {
        requisitionRepository.deleteById(id);
    }
}
