package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.BusinessUnit;
import com.untucapital.usuite.utg.model.Sales;
import com.untucapital.usuite.utg.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@javax.transaction.Transactional
public class SalesService {
    @Autowired
    private SalesRepository salesRepository;

    @Transactional(value = "transactionManager")
    public List<Sales> listAllSales() {
        return salesRepository.findAll();
    }

    @Transactional(value = "transactionManager")
    public void saveSales(Sales sales) {
        salesRepository.save(sales);
    }

    @Transactional(value = "transactionManager")
    public Sales getSales(String id) {
        return salesRepository.findById(id).get();
    }

    @Transactional(value = "transactionManager")
    public void deleteSales(String id) {
        salesRepository.deleteById(id);
    }

    //Get List of Business Units by Id
    @Transactional(value = "transactionManager")
    public List<Sales> lisSalesByLoanId(String id, String businessUnit){
        return salesRepository.findSalestByLoanIdAndBusinessUnitOrderByMonthAsc(id, businessUnit);
    }

    @Transactional(value = "transactionManager")
    public void deleteById(String id) {
        salesRepository.deleteById(id);
    }
}