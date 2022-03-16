package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.BusinessUnit;
import com.untucapital.usuite.utg.repository.BusinessUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BusinessUnitService {
    
    @Autowired
    private final BusinessUnitRepository businessUnitRepository;

    public BusinessUnitService(BusinessUnitRepository businessUnitRepository) {
        this.businessUnitRepository = businessUnitRepository;
    }

    //Add Business Unit
    public void saveBusinessUnit(BusinessUnit businessUnit){
        businessUnitRepository.save(businessUnit);
    }

    //Get all Business units
    public List<BusinessUnit> listBusinessUnits(){
        return businessUnitRepository.findAll();
    }

    //Get List of Business Units by Id
    public List<BusinessUnit> lisBusinessUnitByLoanId(String id){
        return businessUnitRepository.findBusinessUnitByLoanId(id);
    }

    //Get List of Business Units by Id
    public List<BusinessUnit> lisBusinessUnitByLoanId(String id, String businessUnit){
        return businessUnitRepository.findBusinessUnitByLoanId(id);
    }

    //Get Business unit by Id
    public List<BusinessUnit> getBusinessUnitById(String id){
        return businessUnitRepository.findBusinessUnitById(id);
    }

    //Delete Business Unit by BusinessId
    public void deleteBusinessUnit(String id){
        businessUnitRepository.deleteById(id);
    }


}
