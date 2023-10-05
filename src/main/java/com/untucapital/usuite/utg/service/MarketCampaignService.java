package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.MarketCampaign;
import com.untucapital.usuite.utg.model.MarketLeads;
import com.untucapital.usuite.utg.repository.MarketCampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarketCampaignService {
    private final MarketCampaignRepository marketCampaignRepository;

    @Autowired
    public MarketCampaignService(MarketCampaignRepository marketCampaignRepository) {
        this.marketCampaignRepository = marketCampaignRepository;
    }

    public List<MarketCampaign> getAllMarketCampaigns() {
        return marketCampaignRepository.findAll();
    }

    public MarketCampaign getMarketCampaignById(String id) {
        return marketCampaignRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid  ID"));
    }

    public List< MarketCampaign> getMarketCampaignByStatus(String campaignStatus) {
        return marketCampaignRepository.findMarketCampaignByCampaignStatus(campaignStatus);
//                .orElseThrow(() -> new IllegalArgumentException("Invalid  campaignStatus"));
    }

    public MarketCampaign createMarketCampaign(MarketCampaign marketCampaign) {
        return marketCampaignRepository.save(marketCampaign);
    }

    public MarketCampaign updateMarketCampaign(String id, MarketCampaign marketCampaign) {
        MarketCampaign existingCampaign = marketCampaignRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid  ID"));

        existingCampaign.setCampaignName(marketCampaign.getCampaignName());
        existingCampaign.setBranchName(marketCampaign.getBranchName());
        existingCampaign.setCity(marketCampaign.getCity());
        // Set other fields as needed

        existingCampaign.setCampaignStatus(marketCampaign.getCampaignStatus());

        return marketCampaignRepository.save(existingCampaign);
    }


    public MarketCampaign updateMarketCampaignStatus(String id, MarketCampaign marketCampaign) {
        Optional<MarketCampaign> optionalMarketCampaign = marketCampaignRepository.findById(id);
        if (optionalMarketCampaign.isPresent()) {
            MarketCampaign existingMarketCampaign = optionalMarketCampaign.get();

            existingMarketCampaign.setCampaignStatus(marketCampaign.getCampaignStatus());
            return marketCampaignRepository.save(existingMarketCampaign);
        } else {
            return null;
        }
    }



    public void deleteMarketCampaign(String id) {
        marketCampaignRepository.deleteById(id);
    }
}
