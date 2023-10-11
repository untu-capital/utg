package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.Zones;
import com.untucapital.usuite.utg.service.ZonesService;
import com.untucapital.usuite.utg.service.ZonesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/zones")
public class ZonesController {
    @Autowired
    ZonesService zonesService;

    @GetMapping()
    public List<Zones> list() {
        return zonesService.getAllZones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Zones> get(@PathVariable String id) {
        try {
            Zones zones = (Zones) zonesService.getZoneById(id);
            return new ResponseEntity<Zones>(zones, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Zones>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addZones")
    public void add(@RequestBody Zones zones) {
        zonesService.saveZones(zones);
    }

    @DeleteMapping("/deleteZones/{id}")
    public void delete(@PathVariable String id) {
        zonesService.deleteZones(id);
    }
}
