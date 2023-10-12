package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.Events;
import com.untucapital.usuite.utg.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/events")
public class EventsController {
    @Autowired
    EventsService eventsService;

    @GetMapping()
    public List<Events> list() {
        return eventsService.getAllZones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Events> get(@PathVariable String id) {
        try {
            Events events = (Events) eventsService.getZoneById(id);
            return new ResponseEntity<Events>(events, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Events>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addEvents")
    public void add(@RequestBody Events events) {
        eventsService.saveZones(events);
    }

    @DeleteMapping("/deleteEvents/{id}")
    public void delete(@PathVariable String id) {
        eventsService.deleteZones(id);
    }
}
