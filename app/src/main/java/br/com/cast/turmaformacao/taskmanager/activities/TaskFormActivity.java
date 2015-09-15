package br.com.cast.turmaformacao.taskmanager.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.model.entities.Task;
import br.com.cast.turmaformacao.taskmanager.model.services.TaskBusinessServices;
import br.com.cast.turmaformacao.taskmanager.util.FormHelper;

/**
 * Created by Administrador on 15/09/2015.
 */
public class TaskFormActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextDescription;
    private Button buttonSave;
    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_form);

        initTask();
        bindEditTextName();
        bindEditTextDescription();
        bindButtonSave();
    }

    private void initTask() {
        this.task = new Task();
    }

    private void bindButtonSave() {
        buttonSave = (Button) findViewById(R.id.button_saveTask);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String requiredMessage = TaskFormActivity.this.getString(R.string.msg_required);

                if (!FormHelper.validateRequired(requiredMessage,editTextName)) {
                    bindTask();
                    TaskBusinessServices.getInstance().save(task);
                    Toast.makeText(TaskFormActivity.this,getString(R.string.msg_save_sucessfull),Toast.LENGTH_LONG).show();
                    TaskFormActivity.this.finish();
                }

            }
        });
    }

    private void bindTask() {
        task.setName(editTextName.getText().toString());
        task.setDescription(editTextDescription.getText().toString());
    }

    private void bindEditTextName() {
        editTextName = (EditText) findViewById(R.id.editTextTaskName);
        editTextName.setText(task.getDescription() == null ? "" : task.getDescription());

    }

    private void bindEditTextDescription() {
        editTextDescription = (EditText) findViewById(R.id.editTextTaskDescription);
        editTextDescription.setText(task.getDescription() == null ? "" : task.getDescription());
    }


}
