package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.MarketLeads;
import com.untucapital.usuite.utg.repository.MarketLeadsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarketLeadsService {

    private final MarketLeadsRepository marketLeadsRepository;

    @Autowired
    public MarketLeadsService(MarketLeadsRepository marketLeadsRepository) {
        this.marketLeadsRepository = marketLeadsRepository;
    }

    public MarketLeads createMarketLeads(MarketLeads marketLeads) {
        return marketLeadsRepository.save(marketLeads);
    }

    public MarketLeads updateMarketLeads(String id, MarketLeads marketLeads) {
        Optional<MarketLeads> optionalMarketLeads = marketLeadsRepository.findById(id);
        if (optionalMarketLeads.isPresent()) {
            MarketLeads existingMarketLeads = optionalMarketLeads.get();
            existingMarketLeads.setLoanOfficer(marketLeads.getLoanOfficer());
            existingMarketLeads.setBranchName(marketLeads.getBranchName());
            existingMarketLeads.setClientName(marketLeads.getClientName());
            existingMarketLeads.setNatureOfBusiness(marketLeads.getNatureOfBusiness());
            existingMarketLeads.setContactNumber(marketLeads.getContactNumber());
            existingMarketLeads.setContactEmail(marketLeads.getContactEmail());
            existingMarketLeads.setBusinessAddress(marketLeads.getBusinessAddress());
            existingMarketLeads.setPotentialLoanAmount(marketLeads.getPotentialLoanAmount());
            existingMarketLeads.setInteractionComments(marketLeads.getInteractionComments());
            existingMarketLeads.setFollowUpStatus(marketLeads.getFollowUpStatus());
            return marketLeadsRepository.save(existingMarketLeads);
        } else {
            return null;
        }
    }

    public MarketLeads updateMarketLeadsStatus(String id, MarketLeads marketLeads) {
        Optional<MarketLeads> optionalMarketLeads = marketLeadsRepository.findById(id);
        if (optionalMarketLeads.isPresent()) {
            MarketLeads existingMarketLeads = optionalMarketLeads.get();

            existingMarketLeads.setFollowUpStatus(marketLeads.getFollowUpStatus());
            return marketLeadsRepository.save(existingMarketLeads);
        } else {
            return null;
        }
    }

    public MarketLeads getMarketLeadsById(String id) {
        Optional<MarketLeads> optionalMarketLeads = marketLeadsRepository.findById(id);
        return optionalMarketLeads.orElse(null);
    }

    public List<MarketLeads> getAllMarketLeads() {
        return marketLeadsRepository.findAll();
    }

    public void deleteMarketLeads(String id) {
        marketLeadsRepository.deleteById(id);
    }
}
