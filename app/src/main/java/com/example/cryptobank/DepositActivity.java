package com.example.cryptobank;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.cryptobank.tools.CustomToast;
import com.example.cryptobank.tools.MoneyTextWatcher;

import org.json.JSONException;
import org.json.JSONObject;

public class DepositActivity extends AppCompatActivity {

    private String jsonDadosCliente = null;
    ImageView logo = null;
    EditText valor = null;
    Button btDeposito = null;
    Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit2);

        String titulo = getTitle().toString();
        titulo += " - Depósito";
        setTitle(titulo);

        context = getApplicationContext();

        this.receberParametros();
        this.initComponents();
        this.setBasicData();
        this.listenerEvents();
    }

    private void receberParametros() {
        Bundle bundle = getIntent().getExtras();
        this.jsonDadosCliente = bundle.getString("jsonDadosCliente");
    }

    /**
     * Function to init all components
     */
    protected void initComponents() {
        //Image's
        logo = findViewById(R.id.crypto_logo);

        //EditText's
        valor = findViewById(R.id.text_valor);

        //Button's
        btDeposito = findViewById(R.id.bt_transference);
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
        this.valor.addTextChangedListener(new MoneyTextWatcher(this.valor));

        btDeposito.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                API api = new API(getBaseContext(), getAccountNumber(), getToken());
                String value = valor.getText().toString();

                if (!value.equals("0") && !value.equals("") && !value.equals("0,00")) {
                    value = MoneyTextWatcher.formatPriceSave(value);

                    api.deposit(value);
                    CustomToast.showToast("Depósito realizado com sucesso!", context);
                    finish();
                } else {
                    CustomToast.showToast("Valor inválido!", context);
                }
            }
        });
    }

    private String getToken() {
        try {
            return new JSONObject(this.jsonDadosCliente).getString("token");
        } catch (JSONException e) {
            Log.d("JSON_TOKEN", e.getMessage());
        }

        return null;
    }

    private String getJsonAccount() {
        try {
            return new JSONObject(this.jsonDadosCliente).getString("account");
        } catch (JSONException e) {
            Log.d("JSON_ACCOUNT", e.getMessage());
        }

        return null;
    }

    private Integer getAccountNumber() {
        try {
            String jsonAccount = getJsonAccount();
            if (jsonAccount == null) {
                return null;
            }

            return new JSONObject(jsonAccount).getInt("number_account");
        } catch (JSONException e) {
            Log.d("JSON_NUMBER_ACCOUNT", e.getMessage());
        }

        return null;
    }
}