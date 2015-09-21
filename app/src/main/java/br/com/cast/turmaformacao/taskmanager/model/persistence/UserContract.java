package br.com.cast.turmaformacao.taskmanager.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entities.Task;
import br.com.cast.turmaformacao.taskmanager.model.entities.User;

/**
 * Created by Administrador on 21/09/2015.
 */
public final class UserContract {

    public static final String TABLE = "user";
    public static final String ID = "id";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String NAME = "name";
    public static final String EMAIL = "email";

    public static final String[] COLUNS  = {ID,LOGIN,PASSWORD,NAME,EMAIL};

    public static String getCreateTableScript() {

        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(LOGIN + " TEXT NOT NULL, ");
        create.append(PASSWORD+ " TEXT NOT NULL,");
        create.append(NAME + " TEXT NOT NULL, ");
        create.append(EMAIL + " TEXT ");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(User user) {
        ContentValues values = new ContentValues();

        values.put(UserContract.ID, user.getId());
        values.put(UserContract.LOGIN, user.getLogin());
        values.put(UserContract.PASSWORD, user.getPassword());
        values.put(UserContract.NAME, user.getName());
        values.put(UserContract.EMAIL, user.getEmail());

        return values;

    }

    public static User getUser(Cursor cursor){

        User user = null;

        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
           user = new User();

            user.setId(cursor.getLong(cursor.getColumnIndex(UserContract.ID)));
            user.setLogin(cursor.getString(cursor.getColumnIndex(UserContract.LOGIN)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(UserContract.PASSWORD)));
            user.setName(cursor.getString(cursor.getColumnIndex(UserContract.NAME)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(UserContract.EMAIL)));
        }

        return user;

    }

    public static List<User> getUsers(Cursor cursor){
        List<User> users = new ArrayList<>();

        while(cursor.moveToNext()){
            users.add(getUser(cursor));
        }

        return users;
    }

}
