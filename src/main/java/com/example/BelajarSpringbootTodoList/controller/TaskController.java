package com.example.BelajarSpringbootTodoList.controller;

import com.example.BelajarSpringbootTodoList.model.Task;
import com.example.BelajarSpringbootTodoList.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("/")
    public ResponseEntity<String> welcome() {
        return new ResponseEntity<>("Welcome to Task Manager", HttpStatus.OK);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> tasks() {
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") int id) {
        Task task = taskService.getTaskById(id);
        if (task == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(task, HttpStatus.OK);
        }
    }

    @GetMapping("/tasks/{id}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") int id) {
        Task task = taskService.getTaskById(id);
        byte[] imageFile = task.getImageData();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(task.getImageType()))
                .body(imageFile);
    }

    @PostMapping("/tasks")
    public ResponseEntity<?> addTask(@RequestPart Task task, @RequestPart MultipartFile imageFile) {

        try {
            Task new_task = taskService.addTask(task, imageFile);

            return new ResponseEntity<>(new_task, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<?> updateTask(@PathVariable("id") int id, @RequestPart Task task, @RequestPart MultipartFile imageFile) {
        task.setId(id);
        try {
            Task new_task = taskService.updateTask(task, imageFile);

            return new ResponseEntity<>(new_task, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") int id) {
        Task task = taskService.getTaskById(id);
        if (task == null) {
            return new ResponseEntity<>("Task not found", HttpStatus.NOT_FOUND);
        } else {
            taskService.deleteTask(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }
    }


}
