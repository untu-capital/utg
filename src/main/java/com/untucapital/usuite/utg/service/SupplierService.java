package com.untucapital.usuite.utg.service;


import com.untucapital.usuite.utg.model.POSSupplier;
import com.untucapital.usuite.utg.model.Staff;
import com.untucapital.usuite.utg.repository.cms.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author tjchidanika
 * @created 5/9/2023
 */

@Service
@RequiredArgsConstructor
public class SupplierService {
    private final SupplierRepository supplierRepository;

    //Save Supplier
    @Transactional(value = "transactionManager")
    public POSSupplier saveSupplier(POSSupplier posSupplier) {
       return supplierRepository.save(posSupplier);
    }
    //Get Supplier By Id
    @Transactional(value = "transactionManager")
    public POSSupplier getSupplierById(Integer id) {
        return supplierRepository.findById(id).orElse(null);
    }
    //Get All Suppliers
    @Transactional(value = "transactionManager")
    public List<POSSupplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }
    //Update Supplier
    @Transactional(value = "transactionManager")
    public POSSupplier updateSupplier(POSSupplier posSupplier) {
        POSSupplier existingSupplier = supplierRepository.findById(posSupplier.getId()).orElse(null);

        assert existingSupplier != null;
        existingSupplier.setName(posSupplier.getName());
        existingSupplier.setAddress(posSupplier.getAddress());
        existingSupplier.setPhone(posSupplier.getPhone());
        existingSupplier.setContactPerson(posSupplier.getContactPerson());
        existingSupplier.setComment(posSupplier.getComment());
        return supplierRepository.save(existingSupplier);
    }
    //Delete Supplier
    @Transactional(value = "transactionManager")
    public POSSupplier deleteSupplier(Integer id) {
        POSSupplier supplier = supplierRepository.findById(id).orElse(null);
        supplierRepository.deleteById(id);
        return supplier;
    }

}
