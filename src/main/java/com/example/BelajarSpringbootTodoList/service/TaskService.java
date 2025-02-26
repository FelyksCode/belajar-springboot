package com.example.BelajarSpringbootTodoList.service;

import com.example.BelajarSpringbootTodoList.model.Task;
import com.example.BelajarSpringbootTodoList.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskRepo taskRepo;

    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    public Task getTaskById(int id) {
        return taskRepo.findById(id).orElse(null);
    }

    public Task addTask(Task task, MultipartFile imageFile) throws IOException {
        task.setImageName(imageFile.getOriginalFilename());
        task.setImageType(imageFile.getContentType());
        task.setImageData(imageFile.getBytes());
        return taskRepo.save(task);
    }

    public Task updateTask(Task task, MultipartFile imageFile) throws IOException {
        task.setImageName(imageFile.getOriginalFilename());
        task.setImageType(imageFile.getContentType());
        task.setImageData(imageFile.getBytes());
        return taskRepo.save(task);
    }

    public void deleteTask(int id) {
        taskRepo.deleteById(id);
    }
}
