package com.task.controller;

import com.task.exception.TaskNotFoundException;
import com.task.model.Task;
import com.task.service.TaskService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Only ADMIN can create tasks
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

    // Both ADMIN and CUSTOMER can view all tasks
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    // Both ADMIN and CUSTOMER can view tasks by project ID
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Task>> getTasksByProjectId(@PathVariable Long projectId) throws TaskNotFoundException {
        return ResponseEntity.ok(taskService.getTasksByProjectId(projectId));
    }

    // Both ADMIN and CUSTOMER can view tasks by ID
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Only ADMIN can update tasks
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) throws TaskNotFoundException {
        return ResponseEntity.ok(taskService.updateTask(id, taskDetails));
    }

    // Only ADMIN can delete tasks
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }

    // Both ADMIN and CUSTOMER can check if a task exists
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping("/{id}/exist")
    public ResponseEntity<Boolean> existTask(@PathVariable("id") Long id) {
        boolean exists = taskService.existTask(id);
        return ResponseEntity.ok(exists);
    }
}
