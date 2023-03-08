package com.codecool.todolist.models;

import com.codecool.todolist.enums.TaskType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    @Size(min = 3, max = 30)
    private String taskName;
    @NotBlank
    @Size(min = 5, max = 100)
    private String description;
    @NotNull
    @Enumerated(EnumType.STRING)
    private TaskType taskType;
    @NotNull
    private LocalDate deadLine;
    @NotNull
    private Long numberOfDays;
    @NotNull
    private LocalDate estimatedTime;
    private LocalDate finishDate;
    @NotNull
    @CreatedDate
    private LocalDateTime creationDate;
    @NotNull
    private boolean completed = false;
}
