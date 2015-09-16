package br.com.cast.turmaformacao.taskmanager.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entities.Task;

/**
 * Created by Administrador on 16/09/2015.
 */
public final class TaskRepository {

    private TaskRepository() {
        super();
    }

    public static void save(Task task) {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();

        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues values = TaskContract.getContentValues(task);

        if (task.getId() == null) {

            db.insert(TaskContract.TABLE, null, values);

        } else {

            String where = TaskContract.ID + " = ? ";
            String[] params = {task.getId().toString()};
            db.update(TaskContract.TABLE, values, where, params);
        }

        db.close();
        dataBaseHelper.close();

    }

    public static void delete(long id) {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        String where = TaskContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};

        db.delete(TaskContract.TABLE, where, params);

        db.close();
        dataBaseHelper.close();

    }

    public static List<Task> getAll() {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor cursor = db.query(TaskContract.TABLE, TaskContract.COLUNS, null, null, null, null, TaskContract.ID);
        List<Task> values = TaskContract.getTasks(cursor);

        db.close();
        dataBaseHelper.close();

        return values;

    }

}
