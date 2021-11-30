package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.exception.ResourceNotFoundException;
import com.untucapital.usuite.utg.model.AbstractEntity;
import com.untucapital.usuite.utg.service.AbstractService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * @author Chirinda Nyasha Dell 22/11/2021
 */

@RequestMapping(consumes = "application/json", produces = "application/json")
public abstract class AbstractController<T extends AbstractEntity> {

    protected abstract AbstractService<T> getService();

    // Get All Resources
    @GetMapping
    public List<T> getAll() {
        return getService().findAll();
    }

    // Create a new Resource
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody T newResource) {
        T createdResource = getService().save(newResource);

        URI resourceLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdResource.getId())
                .toUri();

        return ResponseEntity.created(resourceLocation).build();
    }

    // Get a Single Resource
    @GetMapping("/{id}")
    public ResponseEntity<T> get(@PathVariable String id) {
        T t = getService().find(id).orElseThrow(() -> new ResourceNotFoundException("Resource", "id", id));
        return ResponseEntity.ok(t);
    }

    // Get Total number of Resources
    @RequestMapping("/count")
    public ResponseEntity<Long> count() {
        long resourcesCount = getService().count();
        return ResponseEntity.ok(resourcesCount);
    }

    // Update a Resource
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody T resourceDetails, @PathVariable String id) {
        T updatedResource = getService().update(resourceDetails, id);
        return ResponseEntity.ok(updatedResource);
    }

    // Delete a Resource
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable String id) {
        getService().delete(id);
        return ResponseEntity.noContent().build();
    }
}
