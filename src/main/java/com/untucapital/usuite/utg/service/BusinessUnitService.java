package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.BusinessUnit;
import com.untucapital.usuite.utg.repository.BusinessUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BusinessUnitService {
    
    @Autowired
    private final BusinessUnitRepository businessUnitRepository;

    public BusinessUnitService(BusinessUnitRepository businessUnitRepository) {
        this.businessUnitRepository = businessUnitRepository;
    }

    //Add Business Unit
    @Transactional(value = "transactionManager")
    public void saveBusinessUnit(BusinessUnit businessUnit){
        businessUnitRepository.save(businessUnit);
    }

    //Get all Business units
    @Transactional(value = "transactionManager")
    public List<BusinessUnit> listBusinessUnits(){
        return businessUnitRepository.findAll();
    }

    //Get List of Business Units by Loan Id
    @Transactional(value = "transactionManager")
    public List<BusinessUnit> lisBusinessUnitByLoanId(String id){
        return businessUnitRepository.findBusinessUnitByLoanId(id);
    }

    //Get List of Business Units by Id
    @Transactional(value = "transactionManager")
    public List<BusinessUnit> lisBusinessUnitByLoanId(String id, String businessUnit){
        return businessUnitRepository.findBusinessUnitByLoanId(id);
    }

    //Get Business unit by Id
    @Transactional(value = "transactionManager")
    public List<BusinessUnit> getBusinessUnitById(String id){
        return businessUnitRepository.findBusinessUnitById(id);
    }

    //Delete Business Unit by BusinessId
    @Transactional(value = "transactionManager")
    public void deleteBusinessUnit(String id){
        businessUnitRepository.deleteById(id);
    }


}
