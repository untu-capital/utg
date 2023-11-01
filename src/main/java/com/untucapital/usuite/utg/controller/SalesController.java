package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.Sales;
import com.untucapital.usuite.utg.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("sales")
public class SalesController {
    @Autowired
    SalesService salesService;

    @GetMapping("getSales")
    public List<Sales> list() {
        return salesService.listAllSales();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sales> get(@PathVariable String id) {
        try {
            Sales sales = salesService.getSales(id);
            return new ResponseEntity<Sales>(sales, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Sales>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/addSales")
    public void add(@RequestBody Sales sales) {
        salesService.saveSales(sales);
    }


    @DeleteMapping("/deleteSales/{Id}")
    public void delete(@PathVariable("Id") String id) {

        salesService.deleteSales(id);
    }
    @DeleteMapping("/delete/{Id}")
    public void deleteById(@PathVariable("Id") String id){
        salesService.deleteById(id);
    }

    //Get list Business Units by Loan Id and groupBy Bsn unit
    @GetMapping("getSalesByLoanIdAndBusinessUnits/{loanId}/{businessUnit}")
    public List<Sales> getSalesByLoanIdAndBusinessUnits(@PathVariable("loanId") String id, @PathVariable("businessUnit") String businessUnit){
        return salesService.lisSalesByLoanId(id, businessUnit);
    }

    }

