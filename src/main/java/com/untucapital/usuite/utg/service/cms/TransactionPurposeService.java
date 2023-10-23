package com.untucapital.usuite.utg.service.cms;

import com.untucapital.usuite.utg.model.cms.TransactionPurpose;
import com.untucapital.usuite.utg.repository.cms.TransactionPurposeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author tjchidanika
 * @created 5/10/2023
 */
@Service
@RequiredArgsConstructor
public class TransactionPurposeService {
    private final TransactionPurposeRepository transactionPurposeRepository;

    //save
    public TransactionPurpose save(TransactionPurpose transactionPurpose){
        Optional<TransactionPurpose> transactionPurpose1 = transactionPurposeRepository.findByName(transactionPurpose.getName());

        if (transactionPurpose1.isPresent()){
            throw new RuntimeException("Transaction Purpose Already Exists");
        }

        return transactionPurposeRepository.save(transactionPurpose);
    }
    //update
    public TransactionPurpose update(TransactionPurpose transactionPurpose){
        TransactionPurpose transactionPurpose1 = transactionPurposeRepository.getById(transactionPurpose.getId());
        if (transactionPurpose1.getId() == null){
            throw new RuntimeException("Transaction Purpose Does Not Exist");
        }

        return transactionPurposeRepository.save(transactionPurpose);
    }
    //delete
    public void delete(TransactionPurpose transactionPurpose){
        TransactionPurpose transactionPurpose1 = transactionPurposeRepository.findById(transactionPurpose.getId()).orElseThrow();
        if (transactionPurpose1.getId() == null){
            throw new RuntimeException("Transaction Purpose Does Not Exist");
        }

        transactionPurposeRepository.delete(transactionPurpose);
    }
    //get all
    public List<TransactionPurpose> getAll(){
        return transactionPurposeRepository.findAll();
    }
    //get by id
    public TransactionPurpose getById(Integer id){
        return transactionPurposeRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Transaction Purpose Does Not Exist")
        );
    }
}
