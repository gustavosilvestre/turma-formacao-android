package br.com.cast.turmaformacao.taskmanager.controllers.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.controllers.adpters.ColorListAdapter;
import br.com.cast.turmaformacao.taskmanager.model.entities.Color;
import br.com.cast.turmaformacao.taskmanager.model.entities.Label;
import br.com.cast.turmaformacao.taskmanager.model.persistence.LabelRepository;
import br.com.cast.turmaformacao.taskmanager.model.services.LabelBusinessServices;
import br.com.cast.turmaformacao.taskmanager.util.FormHelper;

/**
 * Created by Administrador on 17/09/2015.
 */
public class LabelFormActivity extends AppCompatActivity {

    private Spinner spinnerColor;
    private EditText editTextName;
    private EditText editTextDescription;
    private Label label;
    private Color colorSelect;
    private Button buttonListar;
    private static final String PARAM_LABEL = "PARAM_LABEL";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label_form);

        init();
        bindSpinnerColor();
        bindEditTextName();
        bindEditTextDescription();

    }



    private void init() {

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            this.label = (Label) extras.getParcelable(PARAM_LABEL);
        }

        this.label = this.label == null ? new Label() : this.label;

    }

    private void bindEditTextDescription() {
        editTextDescription = (EditText) findViewById(R.id.editTextLabelDescription);
        editTextDescription.setText(label.getDescription() == null ? "" : label.getDescription());
    }

    private void bindEditTextName() {
        editTextName = (EditText) findViewById(R.id.editTextLabelName);
        editTextName.setText(label.getName() == null ? "" : label.getName());
    }

    private void bindLabel() {
        label.setName(editTextName.getText().toString());
        label.setDescription(editTextDescription.getText().toString());
        colorSelect = (Color) spinnerColor.getSelectedItem();
        label.setColor(colorSelect);
    }

    private void bindSpinnerColor() {

        spinnerColor = (Spinner) findViewById(R.id.spinnerColor);
        ColorListAdapter colorAdapter = new ColorListAdapter(LabelFormActivity.this);
        spinnerColor.setAdapter(colorAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_label_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_label_add:
                onMenuDoneClick();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void onMenuDoneClick() {

        String msg = getResources().getString(R.string.msg_required);

        if (!FormHelper.validateRequired(msg, editTextDescription, editTextName)) {
            bindLabel();
            LabelBusinessServices.save(this.label);
            colorSelect  = null;
            Toast.makeText(this, LabelBusinessServices.findAll().toString(), Toast.LENGTH_SHORT).show();
        }
    }

}
