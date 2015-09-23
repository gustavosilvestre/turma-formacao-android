package br.com.cast.turmaformacao.taskmanager.controllers.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.model.entities.Address;
import br.com.cast.turmaformacao.taskmanager.model.entities.User;
import br.com.cast.turmaformacao.taskmanager.model.http.AddressService;
import br.com.cast.turmaformacao.taskmanager.model.persistence.UserRepository;
import br.com.cast.turmaformacao.taskmanager.model.services.UserBusinessServices;

/**
 * Created by Administrador on 14/09/2015.
 */
public class LoginActivity extends AppCompatActivity {

    private EditText editTextLogin;
    private EditText editTextPassword;
    public static User user;
    private Button buttonLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
        bindEditTextLogin();
        bindEditTextPassword();
        bindButtonLogin();

    }

    public void init() {
        user = null;
    }

    public boolean validateLogin() {

        String login = editTextLogin.getText().toString();
        String password = editTextPassword.getText().toString();

        User testeLogin = new User(login,password,"","");

        user = UserBusinessServices.getUserByLoginPassword(testeLogin);

        if (user != null)
            return true;

        return false;
    }

    private void bindButtonLogin() {
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonLoginClick();
            }
        });
    }

    private void onButtonLoginClick() {

        if (validateLogin()) {
            Intent redirectToTaskList = new Intent(LoginActivity.this, TaskListActivity.class);
            startActivity(redirectToTaskList);
            finish();
        } else {
            Toast.makeText(LoginActivity.this, getString(R.string.msg_login_password_incorrect), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_login_add:
                onMenuAddClick();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onMenuAddClick() {
        Intent gotoUserFormActivity = new Intent(LoginActivity.this, UserFormActivity.class);
        startActivity(gotoUserFormActivity);
    }

    private void bindEditTextPassword() {
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
    }

    private void bindEditTextLogin() {
        editTextLogin = (EditText) findViewById(R.id.editTextLogin);
    }

}
