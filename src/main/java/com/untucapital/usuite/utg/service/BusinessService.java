package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.Business;

import com.untucapital.usuite.utg.repository.BusinessRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@javax.transaction.Transactional
public class BusinessService {
    @Autowired
    private BusinessRepository businessRepository;

    @Transactional(value = "transactionManager")
    public List<Business> listAllBusiness() {
        return businessRepository.findAll();
    }

    @Transactional(value = "transactionManager")
    public void saveBusiness(Business business) {
        businessRepository.save(business);
    }

    @Transactional(value = "transactionManager")
    public Business getBusiness(Integer id) {
        return businessRepository.findById(id).get();
    }

    @Transactional(value = "transactionManager")
    public void deleteBusiness(Integer id) {
        businessRepository.deleteById(id);
    }
}
