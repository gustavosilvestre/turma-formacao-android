package br.com.cast.turmaformacao.taskmanager.model.services;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entities.Label;
import br.com.cast.turmaformacao.taskmanager.model.persistence.LabelRepository;
import br.com.cast.turmaformacao.taskmanager.model.persistence.TaskContract;
import br.com.cast.turmaformacao.taskmanager.model.persistence.TaskRepository;

/**
 * Created by Administrador on 17/09/2015.
 */
public class LabelBusinessServices {

    public LabelBusinessServices() {
        super();
    }

    public static List<Label> findAll() {
        return LabelRepository.getAll();
    }

    public static void save(Label label) {
        LabelRepository.save(label);
    }

    public static boolean delete(Label label){

        if (TaskRepository.getCount(TaskContract.LABEL_ID,label.getId()) == 0){
            LabelRepository.delete(label.getId());
            return true;
        }

        return false;

    }

    public static Label getById(Long id){

        return LabelRepository.getById(id);

    }
}
