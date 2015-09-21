package br.com.cast.turmaformacao.taskmanager.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entities.User;

/**
 * Created by Administrador on 21/09/2015.
 */
public final class UserRepository {

    private UserRepository(){
        super();
    }


    public static void save(User user){

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        ContentValues values = UserContract.getContentValues(user);

        db.insert(UserContract.TABLE,null,values);

        dataBaseHelper.close();
        db.close();

    }

    public static List<User> getUserByLogin(String login){

        List<User> users;

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        String selection = UserContract.LOGIN+" = ?";
        String[] params = {login};

        Cursor cursor = db.query(UserContract.TABLE,UserContract.COLUNS,selection,params,null,null,null);

        users = UserContract.getUsers(cursor);

        dataBaseHelper.close();
        db.close();

        return users;
    }

    public static User getUserByLoginPassword(User user){

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        String selection = UserContract.LOGIN+" = ? and "+UserContract.PASSWORD+" = ?";
        String params[] = {user.getLogin(),user.getPassword()};

        Cursor cursor = db.query(UserContract.TABLE, UserContract.COLUNS, selection, params, null, null, UserContract.LOGIN);

        user = UserContract.getUser(cursor);

        dataBaseHelper.close();
        db.close();

        return user;
    }


}
