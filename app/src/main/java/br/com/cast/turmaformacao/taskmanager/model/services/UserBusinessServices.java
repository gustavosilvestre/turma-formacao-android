package br.com.cast.turmaformacao.taskmanager.model.services;

import br.com.cast.turmaformacao.taskmanager.model.entities.User;
import br.com.cast.turmaformacao.taskmanager.model.persistence.UserRepository;

/**
 * Created by Administrador on 21/09/2015.
 */
public final class UserBusinessServices {

    private UserBusinessServices(){
        super();
    }

    public static void save(User user){
        UserRepository.save(user);
    }

    public static User getUserByLoginPassword(User user){
        return UserRepository.getUserByLoginPassword(user);
    }
}
