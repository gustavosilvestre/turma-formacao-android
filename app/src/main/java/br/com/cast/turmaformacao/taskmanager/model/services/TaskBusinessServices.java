package br.com.cast.turmaformacao.taskmanager.model.services;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entities.Task;
import br.com.cast.turmaformacao.taskmanager.model.http.TaskService;
import br.com.cast.turmaformacao.taskmanager.model.persistence.LabelRepository;
import br.com.cast.turmaformacao.taskmanager.model.persistence.TaskRepository;

/**
 * Created by Administrador on 15/09/2015.
 */
public final class TaskBusinessServices {

    private TaskBusinessServices() {
        super();
    }

    public static List<Task> findAll() {
        List<Task> all = TaskRepository.getAll();

        for (Task task : all) {
            task.setLabel(LabelRepository.getById(task.getLabel().getId()));
        }

        return all;
    }

    public static void syncronized() {

        List<Task> tasks = TaskService.getAll();

        for (Task task : tasks) {

            Long id = TaskRepository.getIdByWebId(task.getWeb_id());

            if(id != null){
                task.setId(id);
            }

            save(task);
        }
    }

    public static void save(Task task) {
        TaskRepository.save(task);
    }

    public static void delete(Task task) {
        TaskRepository.delete(task.getId());
    }

}
