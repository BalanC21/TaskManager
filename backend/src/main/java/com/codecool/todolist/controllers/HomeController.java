package com.codecool.todolist.controllers;

import com.codecool.todolist.dtos.TaskDTO;
import com.codecool.todolist.models.Task;
import com.codecool.todolist.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class HomeController {
    private final TaskService taskService;

    public HomeController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping()
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getTasks());
    }

    @GetMapping("/all-completed")
    public ResponseEntity<List<Task>> getAllCompletedTasks() {
        return ResponseEntity.ok(taskService.getCompletedTasks());
    }

    @GetMapping("/all-completed-deadline-ascending/{status}")
    public ResponseEntity<List<Task>> getAllCompletedOrderedByDeadLine(@PathVariable boolean status) {
        return ResponseEntity.ok(taskService.getCompletedOrderedByDeadLine(status));
    }

    @GetMapping("/all-completed-deadline-descending/{status}")
    public ResponseEntity<List<Task>> getAllCompletedOrderedByDeadLineDesc(@PathVariable boolean status) {
        return ResponseEntity.ok(taskService.getCompletedOrderedByDeadLineDesc(status));
    }

    @PostMapping
    public ResponseEntity<?> addTask(@Valid @RequestBody TaskDTO taskDTO) {
        System.out.println(taskDTO);
        try {
            if (taskService.addTask(taskDTO)) {
                return ResponseEntity.ok(Collections.singletonMap("response", "Task added successfully"));
            }
            return ResponseEntity.ok(Collections.singletonMap("response", "Task was not added!"));
        } catch (Exception exception) {
            return ResponseEntity.ok(exception.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        if (taskService.deleteTask(id)) {
            return ResponseEntity.ok(Collections.singletonMap("response", "Task deleted successfully"));
        }
        return ResponseEntity.ok(Collections.singletonMap("response", "Task was not deleted!"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> markTaskDone(@PathVariable Long id) {
        if (taskService.markTaskAsCompleted(id)) {
            return ResponseEntity.ok(Collections.singletonMap("response", "Task was marked successfully!"));
        }
        return ResponseEntity.ok(Collections.singletonMap("response", "Task was not marked !"));
    }
}
