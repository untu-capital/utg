package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.Requisitions;
import com.untucapital.usuite.utg.repository.RequisitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RequisitionService {

    @Autowired
    private RequisitionRepository requisitionRepository;

    public List<Requisitions> getAllRequistions() {
        return requisitionRepository.findAll();
    }

    public void saveRequisition(Requisitions requisitions) {
        requisitionRepository.save(requisitions);
    }

    public Optional<Requisitions> getRequisitionById(String id) {
        return requisitionRepository.findById(id);
    }

    public Optional<Requisitions> getRequisitionByPoNumber(String poNumber) {
        return requisitionRepository.getRequisitionsByPoNumber(poNumber);
    }


    public void deleteRequisition(String id) {
        requisitionRepository.deleteById(id);
    }
}
