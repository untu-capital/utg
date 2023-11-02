package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.MostImportantSuppliers;
import com.untucapital.usuite.utg.repository.MostImportantSuppliersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@javax.transaction.Transactional
public class MostImportantSuppliersService {
    @Autowired
    MostImportantSuppliersRepository mostImportantSuppliersRepository;

    @Transactional(value = "transactionManager")
    public void saveMostImportantSuppliers(MostImportantSuppliers mostImportantSuppliers){
        mostImportantSuppliersRepository.save(mostImportantSuppliers);
    }

    @Transactional(value = "transactionManager")
    public List<MostImportantSuppliers> getMostImportantSuppliersByLoanId(String loanId){
        return mostImportantSuppliersRepository.findByLoanId(loanId);
    }

    @Transactional(value = "transactionManager")
    public void deleteMostImportantSuppliers(String id){
        mostImportantSuppliersRepository.deleteById(id);
    }
}
