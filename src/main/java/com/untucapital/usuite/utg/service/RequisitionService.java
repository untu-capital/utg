package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.DTO.response.RequisitionResponseDTO;
import com.untucapital.usuite.utg.model.po.Requisitions;
import com.untucapital.usuite.utg.repository.RequisitionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@javax.transaction.Transactional
public class RequisitionService {

    @Autowired
    private RequisitionRepository requisitionRepository;

    @Transactional(value = "transactionManager")
    public List<RequisitionResponseDTO> getAllRequistions() {
        List<RequisitionResponseDTO> requisitionResponseDTO = new ArrayList<>();

        List<Requisitions> requisitions = requisitionRepository.findAll();
        for (Requisitions requisition: requisitions){
            RequisitionResponseDTO requisitionResponseDTO1 = new RequisitionResponseDTO();
            BeanUtils.copyProperties(requisition, requisitionResponseDTO1);
            requisitionResponseDTO.add(requisitionResponseDTO1);
        }
        return requisitionResponseDTO;
    }

    @Transactional(value = "transactionManager")
    public void saveRequisition(RequisitionResponseDTO requisitions) {
        Requisitions requisitions1 = new Requisitions();
        BeanUtils.copyProperties(requisitions, requisitions1);
        requisitionRepository.save(requisitions1);
    }

    @Transactional(value = "transactionManager")
    public Optional<RequisitionResponseDTO> getRequisitionById(String id) {
        RequisitionResponseDTO requisitionResponseDTO = new RequisitionResponseDTO();
        log.info("getting requisitions");
        Optional<Requisitions> requisitions = requisitionRepository.findById(id);
        log.info("Requisitons: {}", requisitions);
        if (requisitions.isPresent()){
            Requisitions requisitions1 = requisitions.get();
            BeanUtils.copyProperties(requisitions1, requisitionResponseDTO);

        }
        log.info("RequisitionsDTO: {}", requisitionResponseDTO);
        return Optional.of(requisitionResponseDTO);
    }

    @Transactional(value = "transactionManager")
    public List<RequisitionResponseDTO> getRequisitionByUserId(String id) {
        List<RequisitionResponseDTO> requisitionResponseDTO = new ArrayList<>();

        List<Requisitions> requisitions = requisitionRepository.findRequisitionsByUserId(id);
        for (Requisitions requisition: requisitions){
            RequisitionResponseDTO requisitionResponseDTO1 = new RequisitionResponseDTO();
            BeanUtils.copyProperties(requisition, requisitionResponseDTO1);
            requisitionResponseDTO.add(requisitionResponseDTO1);
        }
        return requisitionResponseDTO;
    }

    public List<RequisitionResponseDTO> findRequisitionsByApproverId(String approverId) {
        return requisitionRepository.findAll().stream()
                .filter(requisition -> requisition.getApprovers() != null && requisition.getApprovers().contains(approverId))
                .map(requisition -> {
                    RequisitionResponseDTO requisitionResponseDTO = new RequisitionResponseDTO();
                    BeanUtils.copyProperties(requisition, requisitionResponseDTO);
                    return requisitionResponseDTO;
                })
                .collect(Collectors.toList());
    }

    public List<RequisitionResponseDTO> findRequisitionsWithPoApprover() {
        return requisitionRepository.findRequisitionsByPoApproverIsNotNullAndPoApproverIsNotLike("")
                .stream()
                .map(requisition -> {
                    RequisitionResponseDTO requisitionResponseDTO = new RequisitionResponseDTO();
                    BeanUtils.copyProperties(requisition, requisitionResponseDTO);
                    return requisitionResponseDTO;
                })
                .collect(Collectors.toList());
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
