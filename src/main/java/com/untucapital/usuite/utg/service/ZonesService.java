package com.untucapital.usuite.utg.service;
import com.untucapital.usuite.utg.model.Bank;
import com.untucapital.usuite.utg.model.Business;
import com.untucapital.usuite.utg.model.Cities;
import com.untucapital.usuite.utg.model.Zones;
import com.untucapital.usuite.utg.repository.ZonesRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@Setter
@RequiredArgsConstructor
@Configuration
public class ZonesService {

    @Autowired
    private ZonesRepository zonesRepository;

    public List<Zones> getAllZones() {
        return zonesRepository.findAll();
    }

    public List<Zones> getZoneById(String id) {
        return (List<Zones>) zonesRepository.findZonesById(id);
    }

    public void saveZones(Zones zones) {
        zonesRepository.save(zones);
    }

    public void deleteZones(String id) {
        zonesRepository.deleteById(id);
    }
}
