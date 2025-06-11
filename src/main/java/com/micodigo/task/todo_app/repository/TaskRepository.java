package com.micodigo.task.todo_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.micodigo.task.todo_app.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
    // Spring Data Jpa automaticamente crea el CRUD

}
 