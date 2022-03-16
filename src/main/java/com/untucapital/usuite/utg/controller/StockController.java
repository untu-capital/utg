package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.DirectCost;
import com.untucapital.usuite.utg.model.Stock;
import com.untucapital.usuite.utg.service.DirectCostService;
import com.untucapital.usuite.utg.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    StockService stockService;

    private static final Logger log = LoggerFactory.getLogger(StockController.class);

    @PostMapping("/addstock")
    public void add(@RequestBody Stock stock) {
        stockService.saveStock(stock);
    }

    @GetMapping("/get_stock/{loanId}")
    public List<Stock> getStockByLoanId(@PathVariable("loanId") String loanId) {
        return stockService.getStockByLoanId(loanId);
    }
}
