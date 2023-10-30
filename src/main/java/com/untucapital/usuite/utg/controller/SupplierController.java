package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.POSSupplier;
import com.untucapital.usuite.utg.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tjchidanika
 * @created 5/9/2023
 */

@RestController
@RequestMapping(value = "pos/supplier")
@RequiredArgsConstructor
public class SupplierController {
    private final SupplierService supplierService;

    //Save Supplier
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public POSSupplier saveSupplier(@RequestBody POSSupplier posSupplier) {
       return supplierService.saveSupplier(posSupplier);
    }

    //Get Supplier By Id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public POSSupplier getSupplierById(@PathVariable Integer id) {
        return supplierService.getSupplierById(id);
    }

    //Get all Suppliers
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<POSSupplier> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    //Update Supplier
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public POSSupplier updateSupplier(@RequestBody POSSupplier posSupplier) {
        System.out.println(posSupplier.toString());
        return supplierService.updateSupplier(posSupplier);
    }

    //Delete Supplier
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public POSSupplier deleteSupplier(@PathVariable Integer id) {
        return supplierService.deleteSupplier(id);
    }
}
