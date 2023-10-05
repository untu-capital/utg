package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.MarketCampaign;
import com.untucapital.usuite.utg.service.MarketCampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/market_campaigns")
public class MarketCampaignController {
    private final MarketCampaignService marketCampaignService;

    @Autowired
    public MarketCampaignController(MarketCampaignService marketCampaignService) {
        this.marketCampaignService = marketCampaignService;
    }

    @GetMapping
    public List<MarketCampaign> getAllMarketCampaigns() {
        return marketCampaignService.getAllMarketCampaigns();
    }

    @GetMapping("/{id}")
    public MarketCampaign getMarketCampaignById(@PathVariable String id) {
        return marketCampaignService.getMarketCampaignById(id);
    }
    @GetMapping("/campaignStatus/{campaignStatus}")
    public List<MarketCampaign> getMarketCampaignByStatus(@PathVariable String campaignStatus) {
        return marketCampaignService.getMarketCampaignByStatus(campaignStatus);
    }

    @PostMapping
    public MarketCampaign createMarketCampaign(@RequestBody MarketCampaign marketCampaign) {
        return marketCampaignService.createMarketCampaign(marketCampaign);
    }

    @PutMapping("/{id}")
    public MarketCampaign updateMarketCampaign(
            @PathVariable String id,
            @RequestBody MarketCampaign marketCampaign
    ) {
        return marketCampaignService.updateMarketCampaign(id, marketCampaign);
    }

    @PutMapping("campaignStatus/{id}")
    public MarketCampaign updateMarketCampaignStatus(
            @PathVariable String id,
            @RequestBody MarketCampaign marketCampaign
    ) {
        return marketCampaignService.updateMarketCampaignStatus(id, marketCampaign);
    }

    @DeleteMapping("/{id}")
    public void deleteMarketCampaign(@PathVariable String id) {
        marketCampaignService.deleteMarketCampaign(id);
    }
}
