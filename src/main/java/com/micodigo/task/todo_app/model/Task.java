package com.micodigo.task.todo_app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity                 // Mapeado de tabal en la base de datos
@Data                   // lombok: getter, setter, toString, equals y hashCode
@NoArgsConstructor      // lombok: genera el constructor sin argumentos
@AllArgsConstructor     // lombok: genera constructor con todos los argumentos
public class Task {
    
    @Id  // indica que es una clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // La BD lo genera autoamticamente
    private long id;

    private String description; // estado de la tarea

    private boolean completed;
}
