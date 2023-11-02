package com.untucapital.usuite.utg.service;
import com.untucapital.usuite.utg.model.Zones;
import com.untucapital.usuite.utg.repository.ZonesRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Getter
@Setter
@RequiredArgsConstructor
@Configuration
public class ZonesService {

    @Autowired
    private ZonesRepository zonesRepository;

    @Transactional(value = "transactionManager")
    public List<Zones> getAllZones() {
        return zonesRepository.findAll();
    }

    @Transactional(value = "transactionManager")
    public List<Zones> getZoneById(String id) {
        return (List<Zones>) zonesRepository.findZonesById(id);
    }

    @Transactional(value = "transactionManager")
    public void saveZones(Zones zones) {
        zonesRepository.save(zones);
    }

    @Transactional(value = "transactionManager")
    public void deleteZones(String id) {
        zonesRepository.deleteById(id);
    }
}

