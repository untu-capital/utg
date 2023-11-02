package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.Stock;
import com.untucapital.usuite.utg.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@javax.transaction.Transactional
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    @Transactional(value = "transactionManager")
    public void  saveStock(Stock stock) {
        stockRepository.save(stock);
    }

    @Transactional(value = "transactionManager")
    public List<Stock> getStockByLoanId(String loanId) {
        return stockRepository.findbyLoanId(loanId);
    }

    @Transactional(value = "transactionManager")
    public void deleteStockById(String id) {
        stockRepository.deleteById(id);
    }
}
