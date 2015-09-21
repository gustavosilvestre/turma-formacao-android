package br.com.cast.turmaformacao.taskmanager.controllers.adpters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.model.entities.Task;

/**
 * Created by Administrador on 15/09/2015.
 */
public class TaskListAdpater extends BaseAdapter {

    private List<Task> taskList;
    private Activity context;

    public TaskListAdpater(Activity context, List<Task> taskList) {
        this.taskList = taskList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return taskList.size();
    }

    @Override
    public Task getItem(int position) {
        return taskList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Task task = getItem(position);

        View taskListItemView = context.getLayoutInflater().inflate(R.layout.list_item_task, parent, false);

        View viewColor = taskListItemView.findViewById(R.id.list_item_task_color);

        int hexColor = android.graphics.Color.parseColor(task.getLabel().getColor().getHex());

        viewColor.setBackgroundColor(hexColor);

        TextView textViewName = (TextView) taskListItemView.findViewById(R.id.list_item_task_name);
        textViewName.setText(task.getName());

        return taskListItemView;
    }

    public void setDataValues(List<Task> values) {
        taskList.clear();
        taskList.addAll(values);
    }

}
