package br.com.cast.turmaformacao.taskmanager.controllers.adpters;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.model.entities.Label;

/**
 * Created by Administrador on 21/09/2015.
 */
public class LabelListAdapter extends BaseAdapter {

    private List<Label> labels;
    private Activity context;
    private int layout;

    public LabelListAdapter(Activity context, List<Label> labels, int layout) {

        this.labels = labels;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return labels.size();
    }

    @Override
    public Label getItem(int position) {
        return labels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Label label = getItem(position);
        View view = context.getLayoutInflater().inflate(layout, parent, false);
        String c = "#ffffff";
        if(label.getColor() != null){
            c = label.getColor().getHex();
        }
        int hexColor = android.graphics.Color.parseColor(c);

        switch (layout) {
            case R.layout.list_item_label_task_form:

                TextView color = (TextView) view.findViewById(R.id.list_item_label_color);
                TextView name = (TextView) view.findViewById(R.id.list_item_label_name);
                color.getBackground().setColorFilter(hexColor, PorterDuff.Mode.SRC);
                name.setText(label.getName());
                break;

            case R.layout.list_item_activity_label:
                TextView colorListActivity = (TextView) view.findViewById(R.id.list_label_activity_item_color);
                TextView nameListActivity = (TextView) view.findViewById(R.id.list_label_activity_item_name);
                TextView descriptionListActivity = (TextView) view.findViewById(R.id.list_label_activity_item_description);

                nameListActivity.setText(label.getName());
                descriptionListActivity.setText(label.getDescription());
                colorListActivity.getBackground().setColorFilter(hexColor, PorterDuff.Mode.SRC);
                break;
        }

        return view;
    }


    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }
}
