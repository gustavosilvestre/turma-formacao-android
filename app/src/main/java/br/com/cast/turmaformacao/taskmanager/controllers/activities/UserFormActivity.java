package br.com.cast.turmaformacao.taskmanager.controllers.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.model.entities.Address;
import br.com.cast.turmaformacao.taskmanager.model.entities.User;
import br.com.cast.turmaformacao.taskmanager.model.http.AddressService;
import br.com.cast.turmaformacao.taskmanager.model.services.UserBusinessServices;
import br.com.cast.turmaformacao.taskmanager.util.FormHelper;

/**
 * Created by Administrador on 21/09/2015.
 */
public class UserFormActivity extends AppCompatActivity{

    private EditText editTextLogin;
    private EditText editTextPassword;
    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextZipCode;
    private EditText editTextType;
    private EditText editTextCity;
    private EditText editTextStreet;
    private EditText editTextNeighborhood;
    private EditText editTextState;

    private User user;

    private class GetAddressTask extends AsyncTask<String,Void,Address> {


        @Override
        protected Address doInBackground(String... params) {

            return AddressService.getAddressByZipCode(params[0]);
        }

        @Override
        protected void onPostExecute(Address address) {
            super.onPostExecute(address);
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_user);

        init();
        bindEditTextEmail();
        bindEditTextLogin();
        bindEditTextPassword();
        bindEditTextName();
        bindAddress();


    }

    private void bindAddress(){
        editTextZipCode = (EditText) findViewById(R.id.user_form_zipcode);
        editTextCity = (EditText) findViewById(R.id.user_form_city);
        editTextStreet = (EditText) findViewById(R.id.user_form_street);
        editTextNeighborhood = (EditText) findViewById(R.id.user_form_neighborhood);
        editTextState = (EditText) findViewById(R.id.user_form_state);
        editTextType = (EditText) findViewById(R.id.user_form_type);
    }

    private void init(){
        user = new User();
    }

    private void bindUser() {
        user.setEmail(editTextEmail.getText().toString());
        user.setName(editTextName.getText().toString());
        user.setLogin(editTextLogin.getText().toString());
        user.setPassword(editTextPassword.getText().toString());
    }

    private void bindEditTextName() {
        editTextName = (EditText) findViewById(R.id.user_form_name);
    }

    private void bindEditTextPassword() {
        editTextPassword = (EditText) findViewById(R.id.user_form_password);
    }

    private void bindEditTextLogin() {
        editTextLogin = (EditText) findViewById(R.id.user_form_login);
    }

    private void bindEditTextEmail() {
        editTextEmail = (EditText) findViewById(R.id.user_form_email);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user_form,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.menu_user_form_done :
                onMenuAddClick();
        }

        return super.onOptionsItemSelected(item);
    }

    private void onMenuAddClick() {

        String msg = getResources().getString(R.string.msg_required);

        if(!FormHelper.validateRequired(msg,editTextLogin,editTextPassword,editTextName,editTextEmail)){
            bindUser();
            UserBusinessServices.save(user);
            Toast.makeText(UserFormActivity.this, "User register sucessfull", Toast.LENGTH_SHORT).show();
            this.finish();
        }

    }
}
