package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.DTO.request.RequisitionsRequestDTO;
import com.untucapital.usuite.utg.DTO.response.RequisitionsResponseDTO;
import com.untucapital.usuite.utg.model.Requisitions;
import com.untucapital.usuite.utg.repository.RequisitionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@javax.transaction.Transactional
public class RequisitionService {

    @Autowired
    private RequisitionRepository requisitionRepository;

    @Transactional(value = "transactionManager")
    public List<RequisitionsResponseDTO> getAllRequistions() {

        List<RequisitionsResponseDTO> response = new ArrayList<>();
        List<Requisitions> requisitionsList= requisitionRepository.findAll();

        for (Requisitions requisition : requisitionsList) {
            RequisitionsResponseDTO responseDTO = new RequisitionsResponseDTO();
            BeanUtils.copyProperties(requisition, responseDTO);
            response.add(responseDTO);
        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public void saveRequisition(RequisitionsRequestDTO request) {

        Requisitions requisitions = new Requisitions();
        BeanUtils.copyProperties(request, requisitions);
        requisitionRepository.save(requisitions);
    }

    @Transactional(value = "transactionManager")
    public RequisitionsResponseDTO getRequisitionById(String id) {

        RequisitionsResponseDTO requisitionsResponseDTO = new RequisitionsResponseDTO();
        Optional<Requisitions> requisitions =  requisitionRepository.findById(id);

        if(requisitions.isPresent()) {
            Requisitions requisitions1 = requisitions.get();
            BeanUtils.copyProperties(requisitions1,requisitionsResponseDTO);

            return requisitionsResponseDTO;
        }else {
            return null;
        }
    }

    @Transactional(value = "transactionManager")
    public RequisitionsResponseDTO getRequisitionByPoNumber(String poNumber) {

        RequisitionsResponseDTO response = new RequisitionsResponseDTO();
        Optional<Requisitions> requisitions = requisitionRepository.getRequisitionsByPoNumber(poNumber);

        if(requisitions.isPresent()) {
            Requisitions requisitions1 = requisitions.get();
            BeanUtils.copyProperties(requisitions1,response);

            return response;
        }else {
            return null;
        }
    }

    @Transactional(value = "transactionManager")
    public void deleteRequisition(String id) {
        requisitionRepository.deleteById(id);
    }
}
