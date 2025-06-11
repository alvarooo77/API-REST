package com.micodigo.task.todo_app.controller;

import java.util.List;
import java.util.Optional;

import com.micodigo.task.todo_app.model.Task;
import com.micodigo.task.todo_app.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.micodigo.task.todo_app.repository.TaskRepository;

@RestController                 // indica que la clase es un controlador REST
@RequestMapping("/api/Tasks")   // Define la URL base para todos los endpoints de este controlador
public class TaskController {

    @Autowired      // Inyecta una instancia de TaskRepository (Spring la proporciona automáticamente)
    private TaskRepository taskRepository;

    // Get /api/task: obtener todas las tareas
    @GetMapping
    public List<Task> getAllTask(){
        return taskRepository.findAll();
    }

    // Get /api/task/{id}: obtener una tarea por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable long id){
        Optional<Task> task = taskRepository.findById(id);
        return task.map(ResponseEntity::ok)         // si la tarea existe devuelve 200 OK y la tarea
            .orElseGet(() -> ResponseEntity.notFound().build()); // si no existe devuleve 404 not found
    }


    // POST  /api/task: Crear una nueva tarea
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task){
        Task savedTask = taskRepository.save(task);
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }


    // PUT /api/tasks/{id}
    @PutMapping
    public ResponseEntity<Task> updateTask(@PathVariable long id, @RequestBody Task taskDetails){
        Optional<Task> task = taskRepository.findById(id);

        if (task.isPresent()) {
            Task existingTask = task.get();
            existingTask.setDescription(taskDetails.getDescription());
            existingTask.setCompleted(taskDetails.isCompleted());
            Task updatedTask = taskRepository.save(existingTask);
            return ResponseEntity.ok(updatedTask);
        } else{
            return ResponseEntity.notFound().build();
        }
    }


    // Delete /api/tasks/{id}
    @DeleteMapping
    public ResponseEntity<Void> deleteTask(@PathVariable long id){
        Optional<Task> task = taskRepository.findById(id);

        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return ResponseEntity.noContent().build();  // devuelve 204 existe sin cuerpo
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
