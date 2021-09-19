package com.tdd.demotdd.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class TodoItem {
    @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Integer id;
    @NonNull
    private String title;
    private LocalDate taskDate;
    private String description;
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    public TodoItem(@NonNull String title, LocalDate taskDate, String description, TaskStatus taskStatus) {
        this.title = title;
        this.taskDate = taskDate;
        this.description = description;
        this.taskStatus = taskStatus;
    }
}
