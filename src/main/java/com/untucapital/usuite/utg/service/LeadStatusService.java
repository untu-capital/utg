package com.untucapital.usuite.utg.service;
import com.untucapital.usuite.utg.model.LeadStatus;
import com.untucapital.usuite.utg.model.Zones;
import com.untucapital.usuite.utg.repository.LeadStatusRepository;
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
public class LeadStatusService {

    @Autowired
    private LeadStatusRepository leadStatusRepository;

    public List<LeadStatus> getAllZones() {
        return leadStatusRepository.findAll();
    }

    public List<LeadStatus> getZoneById(String id) {
        return (List<LeadStatus>) leadStatusRepository.findLeadStatusById(id);
    }

    public void saveZones(LeadStatus leadStatus) {
        leadStatusRepository.save(leadStatus);
    }

    public void deleteZones(String id) {
        leadStatusRepository.deleteById(id);
    }

}

