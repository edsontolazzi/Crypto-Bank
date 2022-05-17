package com.example.cryptobank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.cryptobank.tools.CustomToast;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    ImageView logo = null;
    EditText numAccount = null;
    EditText password = null;
    Button btEntrar = null;
    Context context = null;

    String jsonDadosCliente = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();

        this.initComponents();
        this.setBasicData();
        this.listenerEvents();
    }

    /**
     * Function to init all components
     */
    protected void initComponents() {
        //Image's
        logo = findViewById(R.id.crypto_logo);

        //EditText's
        numAccount = findViewById(R.id.text_valor);
        password = findViewById(R.id.text_senha);

        //Button's
        btEntrar = findViewById(R.id.bt_depositar);
    }

    /**
     * Function to set basic data
     */
    protected void setBasicData() {
        logo.setImageResource(R.drawable.cryptomlogo);
    }

    /**
     * Function to listener events
     */
    protected void listenerEvents() {
        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login() {
        String conta = this.numAccount.getText().toString();
        String senha = this.password.getText().toString();

        (new API(context)).authentication(MainActivity.this, conta, senha);
    }

    public void setJsonDadosCliente(String json) {
        this.jsonDadosCliente = json;
        processarJson();
    }

    private void processarJson() {
        try {
            boolean authenticated = new JSONObject(this.jsonDadosCliente).getBoolean("authenticated");

            if (authenticated) {
                abrirTelaHome();


            } else {
                Log.d("ERRO", "Conta ou senha inválidos");
                CustomToast.showToast("Conta ou senha inválidos", context);
            }
        } catch (JSONException e) {
            Log.d("ERRO", e.getMessage());
            CustomToast.showToast(e.getMessage(), context);
        }
    }

    private void abrirTelaHome() {
        Intent intent = new Intent(this, HomeActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString("jsonDadosCliente", this.jsonDadosCliente);

        intent.putExtras(bundle);
        this.startActivity(intent);
    }
}