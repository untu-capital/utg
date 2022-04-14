package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.MostImportantSuppliers;
import com.untucapital.usuite.utg.model.OwnershipDetails;
import com.untucapital.usuite.utg.repository.MostImportantSuppliersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class MostImportantSuppliersService {
    @Autowired
    MostImportantSuppliersRepository mostImportantSuppliersRepository;

    public void saveMostImportantSuppliers(MostImportantSuppliers mostImportantSuppliers){
        mostImportantSuppliersRepository.save(mostImportantSuppliers);
    }

    public List<MostImportantSuppliers> getMostImportantSuppliersByLoanId(String loanId){
        return mostImportantSuppliersRepository.findByLoanId(loanId);
    }

    public void deleteMostImportantSuppliers(String id){
        mostImportantSuppliersRepository.deleteById(id);
    }
}
