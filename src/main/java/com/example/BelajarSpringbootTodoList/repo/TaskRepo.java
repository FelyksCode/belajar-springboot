package com.example.BelajarSpringbootTodoList.repo;

import com.example.BelajarSpringbootTodoList.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends JpaRepository<Task, Integer> {
}
