package com.codecool.todolist.repositories;

import com.codecool.todolist.models.Task;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<List<Task>> findByCompleted(@NotNull boolean completed);

    Optional<List<Task>> findTaskByCompletedOrderByDeadLineAsc(boolean taskStatus);

    Optional<List<Task>> findTaskByCompletedOrderByDeadLineDesc(boolean taskStatus);
}
