package br.com.cast.turmaformacao.taskmanager.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.cast.turmaformacao.taskmanager.R;

/**
 * Created by Administrador on 14/09/2015.
 */
public class ImcActivity extends AppCompatActivity {

    private EditText editTextPeso;
    private EditText editTextAltura;
    private Button buttonCalcularIMC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);

        bindEditTextAltura();
        bindEditTextPeso();
        bindButtonCalcularIMC();

    }


    private void bindEditTextAltura() {
        editTextAltura = (EditText) findViewById(R.id.editTextAltura);
    }

    private void bindEditTextPeso() {
        editTextPeso = (EditText) findViewById(R.id.editTextPeso);
    }

    private double calculaIMC() {

        double peso = Double.parseDouble(editTextPeso.getText().toString());
        double altura = Double.parseDouble(editTextAltura.getText().toString());

        return peso / Math.pow(altura, 2);
    }

    private String classificacaoDoPeso(double imc) {

        if (imc < 20.7) {
            return "Abaixo do Peso";
        } else if (imc < 26.4) {
            return "No Peso normal";
        } else if (imc < 27.8) {
            return "Marginalmente acima do peso";
        } else if (imc < 31.1) {
            return "Acima do Peso Ideal";
        } else {
            return "Obeso";
        }
    }

    private void bindButtonCalcularIMC() {
        buttonCalcularIMC = (Button) findViewById(R.id.buttonCalcularIMC);
        buttonCalcularIMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double imc = calculaIMC();

                String classificacao = classificacaoDoPeso(imc);
                String mensagem = "IMC : " + imc + "\nClassificacao : " + classificacao;

                Toast.makeText(ImcActivity.this, mensagem, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
