package br.com.cast.turmaformacao.taskmanager.controllers.adpters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.model.entities.Color;

/**
 * Created by Administrador on 17/09/2015.
 */
public class ColorListAdapter extends BaseAdapter {

    private Color[] values;
    private Activity context;
    private int tipo_layout;

    public ColorListAdapter(Activity context) {
        super();
        this.context = context;
        this.values = Color.values();
    }

    @Override
    public int getCount() {
        return values.length;
    }

    @Override
    public Color getItem(int position) {
        return values[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Color color = getItem(position);

        View listItem = context.getLayoutInflater().inflate(R.layout.list_item_spinner_color, parent, false);

        int cor = android.graphics.Color.parseColor(color.getHex());

        TextView background = (TextView) listItem.findViewById(R.id.spinnerItemColor);

        background.setBackgroundColor(cor);
        background.setText(color.toString());

        return listItem;
    }
}
