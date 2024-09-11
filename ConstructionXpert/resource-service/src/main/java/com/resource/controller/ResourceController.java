package com.resource.controller;

import com.resource.exception.ResourceNotFoundException;
import com.resource.model.Resource;
import com.resource.service.ResourceService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.resource.exception.ResourceNotFoundException;
import com.resource.model.Resource;
import com.resource.service.ResourceService;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    // Only ADMIN can create resources
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Resource createResource(@RequestBody Resource resource) {
        return resourceService.createResource(resource);
    }

    // Both ADMIN and CUSTOMER can get all resources
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping
    public List<Resource> getAllResources() {
        return resourceService.getAllResources();
    }

    // Both ADMIN and CUSTOMER can get resources by task ID
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<Resource>> getTasksByProjectId(@PathVariable Long taskId) throws ResourceNotFoundException {
        return ResponseEntity.ok(resourceService.getResourcesByTaskId(taskId));
    }

    // Only ADMIN can update resources
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/resource/{id}")
    public Resource updateResource(@PathVariable Long id, @RequestBody Resource resourceDetails) throws ResourceNotFoundException {
        return resourceService.updateResource(id, resourceDetails);
    }

    // Only ADMIN can delete resources
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable Long id) {
        resourceService.deleteResource(id);
        return ResponseEntity.ok().build();
    }
}
