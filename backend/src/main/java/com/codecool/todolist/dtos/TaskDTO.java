package com.codecool.todolist.dtos;

import com.codecool.todolist.enums.TaskType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TaskDTO {
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
    @FutureOrPresent
    private LocalDate deadline;
    @NotNull
    private Long numberOfDays;

    @Override
    public String toString() {
        return "TaskDTO{" +
                "taskName='" + taskName + '\'' +
                ", description='" + description + '\'' +
                ", taskType=" + taskType +
                ", deadline=" + deadline +
                ", numberOfDays=" + numberOfDays +
                '}';
    }
}
