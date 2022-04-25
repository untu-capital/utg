package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.BusinessUnit;
import com.untucapital.usuite.utg.model.Sales;
import com.untucapital.usuite.utg.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Service
public class SalesService {
    @Autowired
    private SalesRepository salesRepository;

    public List<Sales> listAllSales() {
        return salesRepository.findAll();
    }

    public void saveSales(Sales sales) {
        salesRepository.save(sales);
    }

    public Sales getSales(Integer id) {
        return salesRepository.findById(id).get();
    }

    public void deleteSales(Integer id) {
        salesRepository.deleteById(id);
    }

    //Get List of Business Units by Id
    public List<Sales> lisSalesByLoanId(String id, String businessUnit){
        return salesRepository.findSalestByLoanIdAndBusinessUnitOrderByMonthAsc(id, businessUnit);
    }
}