package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.DirectCost;
import com.untucapital.usuite.utg.model.Stock;
import com.untucapital.usuite.utg.repository.DirectCostRepository;
import com.untucapital.usuite.utg.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    public void  saveStock(Stock stock) {
        stockRepository.save(stock);
    }

    public List<Stock> getStockByLoanId(String loanId) {
        return stockRepository.findbyLoanId(loanId);
    }

    public void deleteStockById(String id) {
        stockRepository.deleteById(id);
    }
}
