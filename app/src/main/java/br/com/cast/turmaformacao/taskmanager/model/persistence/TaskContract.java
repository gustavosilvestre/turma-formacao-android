package br.com.cast.turmaformacao.taskmanager.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entities.Color;
import br.com.cast.turmaformacao.taskmanager.model.entities.Label;
import br.com.cast.turmaformacao.taskmanager.model.entities.Task;

/**
 * Created by Administrador on 16/09/2015.
 */
public final class TaskContract {

    public static final String TABLE = "task";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String LABEL_ID = "label_id";


    public static final String[] COLUNS = {ID, NAME, DESCRIPTION, LABEL_ID};

    private TaskContract() {
        super();
    }

    public static String getCreateTableScript() {

        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(NAME + " TEXT NOT NULL, ");
        create.append(DESCRIPTION + " TEXT, ");
        create.append(LABEL_ID+" INTEGER NOT NULL");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(Task task) {
        ContentValues values = new ContentValues();

        values.put(TaskContract.ID, task.getId());
        values.put(TaskContract.NAME, task.getName());
        values.put(TaskContract.DESCRIPTION, task.getDescription());
        values.put(TaskContract.LABEL_ID,task.getLabel().getId());

        return values;

    }

    public static Task getTask(Cursor cursor) {

        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Task task = new Task();
            task.setId(cursor.getLong(cursor.getColumnIndex(TaskContract.ID)));
            task.setName(cursor.getString(cursor.getColumnIndex(TaskContract.NAME)));
            task.setDescription(cursor.getString(cursor.getColumnIndex(TaskContract.DESCRIPTION)));

            task.setLabel(new Label());
            task.getLabel().setId(cursor.getLong(cursor.getColumnIndex(TaskContract.LABEL_ID)));

            return task;
        }

        return null;

    }

    public static List<Task> getTasks(Cursor cursor) {

        List<Task> tasks = new ArrayList<>();

        while (cursor.moveToNext()) {
            tasks.add(getTask(cursor));
        }

        return tasks;

    }


}
