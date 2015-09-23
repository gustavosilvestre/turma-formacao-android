package br.com.cast.turmaformacao.taskmanager.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entities.Label;
import br.com.cast.turmaformacao.taskmanager.model.entities.Task;

/**
 * Created by Administrador on 17/09/2015.
 */
public class LabelRepository {

    private LabelRepository() {
        super();
    }

    public static void save(Label label) {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();

        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues values = LabelContract.getContentValues(label);

        if (label.getId() == null) {

            db.insert(LabelContract.TABLE, null, values);

        } else {

            String where = LabelContract.ID + " = ? ";
            String[] params = {label.getId().toString()};
            db.update(LabelContract.TABLE, values, where, params);
        }

        db.close();
        dataBaseHelper.close();

    }

    public static List<Label> getAll() {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        Cursor cursor = db.query(LabelContract.TABLE, LabelContract.COLUNS, null, null, null, null, LabelContract.ID);
        List<Label> values = LabelContract.getLabels(cursor);

        db.close();
        dataBaseHelper.close();

        return values;
    }

    public static Label getById(long id) {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        String where = LabelContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};

        Cursor cursor = db.query(LabelContract.TABLE, LabelContract.COLUNS, where, params, null, null, null);

        Label label = LabelContract.getLabel(cursor);

        db.close();
        dataBaseHelper.close();

        return label;
    }


    public static void delete(long id) {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        String where = LabelContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};

        db.delete(LabelContract.TABLE, where, params);

        db.close();
        dataBaseHelper.close();

    }


}
