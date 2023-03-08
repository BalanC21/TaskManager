package com.codecool.todolist.services;

import com.codecool.todolist.dtos.TaskDTO;
import com.codecool.todolist.exceptions.CompletedTasksException;
import com.codecool.todolist.exceptions.TaskNotFoundException;
import com.codecool.todolist.models.Task;
import com.codecool.todolist.repositories.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final ValidationService validation;

    public List<Task> getTasks() {
        return taskRepository.findByCompleted(false).orElseThrow(CompletedTasksException::new);
    }

    public List<Task> getCompletedTasks() {
        return taskRepository.findByCompleted(true).orElseThrow(CompletedTasksException::new);
    }

    public List<Task> getCompletedOrderedByDeadLine(boolean status) {
        return taskRepository.findTaskByCompletedOrderByDeadLineAsc(status).orElseThrow(CompletedTasksException::new);
    }

    public List<Task> getCompletedOrderedByDeadLineDesc(boolean status) {
        return taskRepository.findTaskByCompletedOrderByDeadLineDesc(status).orElseThrow(CompletedTasksException::new);
    }

    @Transactional
    public boolean addTask(TaskDTO taskDTO) {
        System.out.println(validation.isInEstimatedTime(taskDTO) + " validation.isInEstimatedTime(taskDTO)");
        if (validation.isInEstimatedTime(taskDTO)) return false;
        if (validation.isTaskBeforeDeadline(taskDTO)) return false;
        taskRepository.save(toTask(taskDTO));
        return true;
    }

    public boolean markTaskAsCompleted(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        if (task.isCompleted()) return false;
        markTaskCompleted(task);
        taskRepository.save(task);
        return true;
    }

    public boolean deleteTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        if (task.isCompleted()) return false;
        taskRepository.delete(task);
        return true;
    }

    private Task toTask(TaskDTO taskDTO) {
        Task newTask = new Task();
        newTask.setTaskName(taskDTO.getTaskName());
        newTask.setDescription(taskDTO.getDescription());
        newTask.setTaskType(taskDTO.getTaskType());
        newTask.setNumberOfDays(taskDTO.getNumberOfDays());
        newTask.setEstimatedTime(LocalDate.now().plusDays(taskDTO.getNumberOfDays()));
        newTask.setDeadLine(taskDTO.getDeadline());
        newTask.setCreationDate(LocalDateTime.now());
        return newTask;
    }

    private void markTaskCompleted(Task task) {
        task.setFinishDate(LocalDate.now());
        task.setCompleted(true);
    }
}