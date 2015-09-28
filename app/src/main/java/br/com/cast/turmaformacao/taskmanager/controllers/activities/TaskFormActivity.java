package br.com.cast.turmaformacao.taskmanager.controllers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.controllers.adpters.LabelListAdapter;
import br.com.cast.turmaformacao.taskmanager.model.entities.Label;
import br.com.cast.turmaformacao.taskmanager.model.entities.Task;
import br.com.cast.turmaformacao.taskmanager.model.http.TaskService;
import br.com.cast.turmaformacao.taskmanager.model.services.LabelBusinessServices;
import br.com.cast.turmaformacao.taskmanager.model.services.TaskBusinessServices;
import br.com.cast.turmaformacao.taskmanager.util.FormHelper;

/**
 * Created by Administrador on 15/09/2015.
 */
public class TaskFormActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextDescription;
    private Spinner spinner;
    private Task task;
    private ImageButton buttonNewLabel;
    public static final String PARAM_TASK = "PARAM_TASK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_form);

        initTask();
        bindEditTextName();
        bindEditTextDescription();
        bindSpinner();
        bindButtonNewLabel();
    }

    private void bindButtonNewLabel() {
        buttonNewLabel = (ImageButton) findViewById(R.id.task_form_button_newLabel);
        buttonNewLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtoNewLabelClick();
            }
        });
    }

    private void onButtoNewLabelClick() {
        Intent gotoLabelFormActivity = new Intent(TaskFormActivity.this, LabelFormActivity.class);
        startActivity(gotoLabelFormActivity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        onUpdateSpinnerList();
    }

    private void bindSpinner() {
        spinner = (Spinner) findViewById(R.id.task_form_spinner);
        onUpdateSpinnerList();
    }

    private void onUpdateSpinnerList() {
        List<Label> labels = LabelBusinessServices.findAll();
        spinner.setAdapter(new LabelListAdapter(TaskFormActivity.this,labels,R.layout.list_item_label_task_form));
        if (task.getLabel() != null) {
            spinner.setSelection(labels.indexOf(task.getLabel()));
        }
    }


    private void initTask() {
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            this.task = (Task) extras.getParcelable(PARAM_TASK);
        }

        this.task = this.task == null ? new Task() : this.task;
    }


    private void bindTask() {
        task.setName(editTextName.getText().toString());
        task.setDescription(editTextDescription.getText().toString());
    }

    private void bindLabel() {
        Label label = (Label) spinner.getSelectedItem();
        task.setLabel(label);
    }

    private void bindEditTextName() {
        editTextName = (EditText) findViewById(R.id.editTextTaskName);
        editTextName.setText(task.getName() == null ? "" : task.getName());
    }

    private void bindEditTextDescription() {
        editTextDescription = (EditText) findViewById(R.id.editTextTaskDescription);
        editTextDescription.setText(task.getDescription() == null ? "" : task.getDescription());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_task_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_task_done:
                onMenuTaskDone();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void onMenuTaskDone() {

        String msg = getResources().getString(R.string.msg_required);

        if (!FormHelper.validateRequired(msg, editTextName, editTextDescription)) {
            bindTask();
            bindLabel();
            TaskBusinessServices.save(task);
            TaskService.save(task);
            Toast.makeText(TaskFormActivity.this, "Task save sucessfull", Toast.LENGTH_SHORT).show();
        }
    }
}
