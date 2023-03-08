package com.codecool.todolist.services;

import com.codecool.todolist.dtos.TaskDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ValidationService {
    public boolean isTaskBeforeDeadline(TaskDTO taskDTO) {
        LocalDate present = LocalDate.now();
        return taskDTO.getDeadline().isBefore(present);
    }

    public boolean isInEstimatedTime(TaskDTO taskDTO) {
        LocalDate deadline = taskDTO.getDeadline();
        LocalDate estimated = LocalDate.now().plusDays(taskDTO.getNumberOfDays());
        System.out.println(estimated + " Estimated");
        System.out.println(deadline + " Deadline");
        if (taskDTO.getNumberOfDays() < 0) {
            return false;
        } else return !deadline.isAfter(estimated);
    }
}


