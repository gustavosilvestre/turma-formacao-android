package br.com.cast.turmaformacao.taskmanager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.adpters.TaskListAdpater;
import br.com.cast.turmaformacao.taskmanager.model.entities.Task;
import br.com.cast.turmaformacao.taskmanager.model.services.TaskBusinessServices;

/**
 * Created by Administrador on 15/09/2015.
 */
public class TaskListActivity extends AppCompatActivity {

    private ListView listViewTaskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        listViewTaskList = (ListView) findViewById(R.id.listViewTaskList);
        List<Task> values = TaskBusinessServices.getInstance().findAll();
        listViewTaskList.setAdapter(new TaskListAdpater(this, values));
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
                onMenuClick();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {

        TaskListAdpater adapter = (TaskListAdpater) listViewTaskList.getAdapter();
        List<Task> values = TaskBusinessServices.getInstance().findAll();
        adapter.setDataValues(values);
        adapter.notifyDataSetChanged();

        super.onResume();
    }

    private void onMenuClick() {
        Intent goToTaskFormActivity = new Intent(TaskListActivity.this, TaskFormActivity.class);
        startActivity(goToTaskFormActivity);
    }
}
