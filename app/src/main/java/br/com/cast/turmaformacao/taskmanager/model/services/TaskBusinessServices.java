package br.com.cast.turmaformacao.taskmanager.model.services;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entities.Task;
import br.com.cast.turmaformacao.taskmanager.model.persistence.TaskRepository;

/**
 * Created by Administrador on 15/09/2015.
 */
public final class TaskBusinessServices {

    private TaskBusinessServices() {
        super();
    }

    public static List<Task> findAll() {
        return TaskRepository.getAll();
    }

    public static void save(Task task) {
        TaskRepository.save(task);
    }

    public static void delete(Task task){
        TaskRepository.delete(task.getId());
    }

}
