package br.com.cast.turmaformacao.taskmanager.controllers.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.controllers.adpters.LabelListAdapter;
import br.com.cast.turmaformacao.taskmanager.model.entities.Label;
import br.com.cast.turmaformacao.taskmanager.model.services.LabelBusinessServices;

/**
 * Created by Administrador on 22/09/2015.
 */
public class LabelListActivity extends AppCompatActivity{

    private ListView listViewLabel;
    private Label selectedLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label_list);
        bindListViewLabel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        onUpdateList();
    }

    private void onUpdateList(){
        List<Label> labels = LabelBusinessServices.findAll();
        listViewLabel.setAdapter(new LabelListAdapter(LabelListActivity.this, labels, R.layout.list_item_activity_label));
        LabelListAdapter adapter = (LabelListAdapter) listViewLabel.getAdapter();
        adapter.notifyDataSetChanged();
    }


    private void bindListViewLabel() {
        listViewLabel = (ListView) findViewById(R.id.listLabelView);
        listViewLabel.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                selectedLabel = (Label) listViewLabel.getAdapter().getItem(position);
                return false;
            }
        });
        registerForContextMenu(listViewLabel);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context_list, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_editar:
                onMenuEditar();
                break;
            case R.id.menu_excluir:
                onMenuExcluir();
                break;
        }

        return super.onContextItemSelected(item);
    }

    private void onMenuEditar(){

        Intent gotoLabelFormActivity = new Intent(LabelListActivity.this, LabelFormActivity.class);
        gotoLabelFormActivity.putExtra(LabelFormActivity.PARAM_LABEL, selectedLabel);
        startActivity(gotoLabelFormActivity);
        finish();

    }

    private void onMenuExcluir() {

        new AlertDialog.Builder(LabelListActivity.this)
                .setTitle(getResources().getString(R.string.lbl_confirm))
                .setMessage(getResources().getString(R.string.msg_confirm_delete))
                .setPositiveButton(getResources().getString(R.string.lbl_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(LabelBusinessServices.delete(selectedLabel)){
                            onUpdateList();
                            Toast.makeText(LabelListActivity.this,getResources().getString(R.string.msg_delete_sucessfull), Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(LabelListActivity.this,"Essa label esta vinculada com uma tarefa!",Toast.LENGTH_SHORT).show();
                        }
                    }
                }).setNeutralButton(getResources().getString(R.string.lbl_no),null).create().show();
    }
}