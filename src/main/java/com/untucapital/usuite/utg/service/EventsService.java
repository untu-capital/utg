package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.Events;
import com.untucapital.usuite.utg.repository.EventsRepository;
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
public class EventsService {

    @Autowired
    private EventsRepository eventsRepository;

    public List<Events> getAllZones() {
        return eventsRepository.findAll();
    }

    public List<Events> getZoneById(String id) {
        return (List<Events>) eventsRepository.findEventsById(id);
    }

    public void saveZones(Events leadStatus) {
        eventsRepository.save(leadStatus);
    }

    public void deleteZones(String id) {
        eventsRepository.deleteById(id);
    }

}

