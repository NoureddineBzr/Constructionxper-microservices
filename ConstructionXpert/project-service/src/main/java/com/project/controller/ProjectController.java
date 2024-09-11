package com.project.controller;

import com.project.exception.ProjectNotFoundException;
import com.project.model.Project;
import com.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // Only ADMIN can create projects
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return projectService.createProject(project);
    }

    // Only ADMIN can update projects
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody Project projectDetails) throws ProjectNotFoundException {
        return projectService.updateProject(id, projectDetails);
    }

    // Only ADMIN can delete projects
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok().build();
    }

    // Both ADMIN and CUSTOMER can view all projects
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    // Both ADMIN and CUSTOMER can check if a project exists
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping("/{id}/exist")
    public ResponseEntity<Boolean> existProject(@PathVariable("id") Long id) {
        boolean exist = projectService.existProject(id);
        return ResponseEntity.ok(exist);
    }
}
