package br.com.cast.turmaformacao.taskmanager.model.services;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entities.Task;

/**
 * Created by Administrador on 15/09/2015.
 */
public final class TaskBusinessServices {

    private List<Task> values = new ArrayList<>();
    private long count = 0;

    private static class Singleton {
        private static TaskBusinessServices INSTANCE = new TaskBusinessServices();
    }

    private TaskBusinessServices() {
        super();
    }

    public static TaskBusinessServices getInstance() {
        return Singleton.INSTANCE;
    }

    public List<Task> findAll() {

        ArrayList<Task> tasks = new ArrayList<>();
        tasks.addAll(values);
        return tasks;

    }

    public void save(Task task) {

        task.setId(task.getId() == null ? ++count : task.getId());
        values.add(task);

    }
}
