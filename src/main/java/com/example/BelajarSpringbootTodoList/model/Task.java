package com.example.BelajarSpringbootTodoList.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private boolean completed;
    private String type;

    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private Date dueDate;
    private Date createdAt;
    private Date updatedAt;

    private String imageName;
    private String imageType;

    @Lob
    private byte[] imageData;
}
