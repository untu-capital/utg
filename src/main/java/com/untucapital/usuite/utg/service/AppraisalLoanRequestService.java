package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.Business;

import com.untucapital.usuite.utg.repository.BusinessRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BusinessService {
    @Autowired
    private BusinessRepository businessRepository;
    public List<Business> listAllBusiness() {
        return businessRepository.findAll();
    }

    public void saveBusiness(Business business) {
        businessRepository.save(business);
    }

    public Business getBusiness(Integer id) {
        return businessRepository.findById(id).get();
    }

    public void deleteBusiness(Integer id) {
        businessRepository.deleteById(id);
    }
}
