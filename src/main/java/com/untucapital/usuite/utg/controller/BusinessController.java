package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.Business;

import com.untucapital.usuite.utg.service.BusinessService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/business")
public class BusinessController {
    @Autowired
    BusinessService businessService;

    @GetMapping("/getBusiness")
    public List<Business> list() {
        return businessService.listAllBusiness();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Business> get(@PathVariable Integer id) {
        try {
            Business business = businessService.getBusiness(id);
            return new ResponseEntity<Business>(business, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Business>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/addBusiness")
    public void add(@RequestBody Business business) {
        businessService.saveBusiness(business);
    }


    @DeleteMapping("/deleteBusiness/{id}")
    public void delete(@PathVariable Integer id) {

        businessService.deleteBusiness(id);
    }
}
