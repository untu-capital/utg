package com.untucapital.usuite.utg.service.cms;


import com.untucapital.usuite.utg.DTO.ApproverRequest;
import com.untucapital.usuite.utg.DTO.AuditTrailInitiatorRequest;
import com.untucapital.usuite.utg.DTO.ChangeAmountRequest;
import com.untucapital.usuite.utg.model.FollowUpDiary;
import com.untucapital.usuite.utg.model.cms.AuditTrail;
import com.untucapital.usuite.utg.model.cms.PettyCashPayments;
import com.untucapital.usuite.utg.repository.cms.AuditTrailRepository;
import com.untucapital.usuite.utg.repository.cms.PettyCashPaymentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author tjchidanika
 * @created 28/9/2023
 */

@Service
@RequiredArgsConstructor
public class PettyCashPaymentsService {

    private  final PettyCashPaymentsRepository pettyCashPaymentsRepository;

    //Get All
    @Transactional(value = "transactionManager")
    public List<PettyCashPayments> getAllPettyCashPayments(){
        return pettyCashPaymentsRepository.findAll();
    }
    //Get By Id
    @Transactional(value = "transactionManager")
    public PettyCashPayments getPettyCashPaymentsById(String id){
        return pettyCashPaymentsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Petty Cash Payment not found"));
    }

    @Transactional(value = "transactionManager")
    public PettyCashPayments updatePettyCashPayments(String id, PettyCashPayments pettyCashPayments) {
        Optional<PettyCashPayments> optionalPettyCashPayments = pettyCashPaymentsRepository.findById(id);
        if (optionalPettyCashPayments.isPresent()) {
            PettyCashPayments existingPettyCashPayment = optionalPettyCashPayments.get();
//            existingPettyCashPayment.setAmount(pettyCashPayments.getAmount());
//            existingPettyCashPayment.setDate(pettyCashPayments.getDate());
//            existingPettyCashPayment.setCount(pettyCashPayments.getCount());
//            existingPettyCashPayment.setName(pettyCashPayments.getName());
            existingPettyCashPayment.setFromAccount(pettyCashPayments.getFromAccount());
//            existingPettyCashPayment.setFirstApprover(pettyCashPayments.getFirstApprover());
//            existingPettyCashPayment.setRequesitionName(pettyCashPayments.getRequesitionName());
            existingPettyCashPayment.setToAccount(pettyCashPayments.getToAccount());
            existingPettyCashPayment.setSecondApprover(pettyCashPayments.getSecondApprover());
//            existingPettyCashPayment.setPurchaseOrderNumber(pettyCashPayments.getPurchaseOrderNumber());
//            existingPettyCashPayment.setTransType(pettyCashPayments.getTransType());
            existingPettyCashPayment.setNotes(pettyCashPayments.getNotes());
            existingPettyCashPayment.setStatus(pettyCashPayments.getStatus());


            return pettyCashPaymentsRepository.save(existingPettyCashPayment);
        } else {
            return null;
        }

    }
}
