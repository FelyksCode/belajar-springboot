package com.example.BelajarSpringbootTodoList.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    @GetMapping("/")
    public String welcome(){
        return "Welcome to Task Controller";
    }

}
