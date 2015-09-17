package br.com.cast.turmaformacao.taskmanager.controllers.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.controllers.adpters.TaskListAdpater;
import br.com.cast.turmaformacao.taskmanager.model.entities.Task;
import br.com.cast.turmaformacao.taskmanager.model.services.TaskBusinessServices;

/**
 * Created by Administrador on 15/09/2015.
 */
public class TaskListActivity extends AppCompatActivity {

    private ListView listViewTaskList;
    private Task selectedTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        bindTaskList();
    }

    private void bindTaskList() {

        listViewTaskList = (ListView) findViewById(R.id.listViewTaskList);
        registerForContextMenu(listViewTaskList);

        listViewTaskList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TaskListAdpater adapter = (TaskListAdpater) listViewTaskList.getAdapter();
                selectedTask = adapter.getItem(position);
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_task_list, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_add:
                onMenuAddClick();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        onUpdateList();
        super.onResume();
    }

    private void onUpdateList() {
        List<Task> values = TaskBusinessServices.findAll();
        listViewTaskList.setAdapter(new TaskListAdpater(this, values));
        TaskListAdpater adapter = (TaskListAdpater) listViewTaskList.getAdapter();
        adapter.notifyDataSetChanged();
    }

    private void onMenuAddClick() {
        Intent goToTaskFormActivity = new Intent(TaskListActivity.this, TaskFormActivity.class);
        startActivity(goToTaskFormActivity);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context_task_list, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_excluir:
                onMenuDeleteClick();
                break;
            case R.id.menu_editar:
                onMenuUpdateClick();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void onMenuUpdateClick() {

        Intent goToTaskForm = new Intent(TaskListActivity.this, TaskFormActivity.class);
        goToTaskForm.putExtra(TaskFormActivity.PARAM_TASK,selectedTask);
        startActivity(goToTaskForm);


    }

    private void onMenuDeleteClick() {

        new AlertDialog.Builder(TaskListActivity.this)
                .setTitle(R.string.lbl_confirm)
                .setMessage(R.string.msg_confirm_delete)
                .setPositiveButton(R.string.lbl_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TaskBusinessServices.delete(selectedTask);
                        selectedTask = null;
                        String message = getString(R.string.msg_delete_sucessfull);
                        onUpdateList();
                        Toast.makeText(TaskListActivity.this, message, Toast.LENGTH_LONG).show();

                    }
                })
                .setNeutralButton(R.string.lbl_no, null)
                .create()
                .show();
    }
}
