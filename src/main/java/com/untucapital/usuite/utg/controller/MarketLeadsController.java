package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.MarketLeads;
import com.untucapital.usuite.utg.repository.MarketLeadsRepository;
import com.untucapital.usuite.utg.service.MarketLeadsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marketLeads")
public class MarketLeadsController {

    private final MarketLeadsService marketLeadsService;

    private final MarketLeadsRepository marketLeadsRepository;

    @Autowired
    public MarketLeadsController(MarketLeadsService marketLeadsService, MarketLeadsRepository marketLeadsRepository) {
        this.marketLeadsService = marketLeadsService;
        this.marketLeadsRepository = marketLeadsRepository;
    }

    @GetMapping
    public ResponseEntity<List<MarketLeads>> getAllMarketLeads() {
        List<MarketLeads> marketLeadsList = marketLeadsService.getAllMarketLeads();
        return new ResponseEntity<>(marketLeadsList, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<MarketLeads> createMarketLead(@RequestBody MarketLeads marketLeads) {
        MarketLeads createdMarketLeads = marketLeadsService.createMarketLeads(marketLeads);
        return new ResponseEntity<>(createdMarketLeads, HttpStatus.CREATED);
    }

    @GetMapping("/branch/{branch}")
    public ResponseEntity<List<MarketLeads>> getMarketLeadsByBranch(@PathVariable String branch) {
        List<MarketLeads> marketLeadsList = marketLeadsRepository.findMarketLeadsByBranchName(branch);
        return new ResponseEntity<>(marketLeadsList, HttpStatus.OK);
    }

    @GetMapping("/loanOfficer/{loanOfficer}")
    public ResponseEntity<List<MarketLeads>> getMarketLeadsByLoanOfficer(@PathVariable String loanOfficer) {
        List<MarketLeads> marketLeadsList = marketLeadsRepository.findMarketLeadsByLoanOfficer(loanOfficer);
        return new ResponseEntity<>(marketLeadsList, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<MarketLeads> updateMarketLead(
            @PathVariable("id") String id, @RequestBody MarketLeads marketLeads) {
        MarketLeads updatedMarketLeads = marketLeadsService.updateMarketLeads(id, marketLeads);
        if (updatedMarketLeads != null) {
            return new ResponseEntity<>(updatedMarketLeads, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("status/{id}")
    public ResponseEntity<MarketLeads> updateMarketLeadStatus(
            @PathVariable("id") String id, @RequestBody MarketLeads marketLeads) {
        MarketLeads updatedMarketLeadStatus = marketLeadsService.updateMarketLeadsStatus(id, marketLeads);
        if (updatedMarketLeadStatus != null) {
            return new ResponseEntity<>(updatedMarketLeadStatus, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<MarketLeads> getMarketLeadById(@PathVariable("id") String id) {
        MarketLeads marketLeads = marketLeadsService.getMarketLeadsById(id);
        if (marketLeads != null) {
            return new ResponseEntity<>(marketLeads, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarketLead(@PathVariable("id") String id) {
        marketLeadsService.deleteMarketLeads(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
