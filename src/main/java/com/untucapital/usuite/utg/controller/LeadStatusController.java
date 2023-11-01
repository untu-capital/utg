package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.LeadStatus;
import com.untucapital.usuite.utg.service.LeadStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/leadStatus")
public class LeadStatusController {
    @Autowired
    LeadStatusService leadStatusService;


    @GetMapping()
    public List<LeadStatus> list() {
        return leadStatusService.getAllZones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeadStatus> get(@PathVariable String id) {
        try {
            LeadStatus leadStatus = (LeadStatus) leadStatusService.getZoneById(id);
            return new ResponseEntity<LeadStatus>(leadStatus, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<LeadStatus>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addLeadStatus")
    public void add(@RequestBody LeadStatus leadStatus) {
        leadStatusService.saveZones(leadStatus);
    }


    @DeleteMapping("/deleteLeadStatus/{id}")
    public void delete(@PathVariable String id) {
        leadStatusService.deleteZones(id);
    }
}
