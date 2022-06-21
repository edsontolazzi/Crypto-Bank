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

import org.json.JSONException;
import org.json.JSONObject;

public class TransferenceActivity extends AppCompatActivity {

    private String jsonDadosCliente = null;
    private String saldo = null;
    ImageView logo = null;
    EditText valor = null;
    EditText contaDestino = null;
    Button btDeposito = null;
    Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transference);

        String titulo = getTitle().toString();
        titulo += " - Transferência";
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
        this.saldo = bundle.getString("saldo");
    }

    /**
     * Function to init all components
     */
    protected void initComponents() {
        //Image's
        logo = findViewById(R.id.crypto_logo);

        //EditText's
        valor = findViewById(R.id.text_valor);
        contaDestino = findViewById(R.id.text_conta_destino);

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
        btDeposito.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                API api = new API(getBaseContext(), getAccountNumber(), getToken());
                String value = valor.getText().toString();
                String conta = contaDestino.getText().toString();

                if (!value.equals("0") && !value.equals("")) {
                    if (!conta.equals("") && conta.length() == 6) {
                        if (Double.parseDouble(saldo) >= Double.parseDouble(value)) {
                            api.transference(value, conta, TransferenceActivity.this);
                        } else {
                            CustomToast.showToast("Saldo insuficiente!", context);
                        }
                    } else {
                        CustomToast.showToast("Conta destino inválida!", context);
                    }
                } else {
                    CustomToast.showToast("Valor inválido!", context);
                }
            }
        });
    }

    public void finalizeTransference() {
        CustomToast.showToast("Transferencia realizada com sucesso!", context);
        finish();
    }

    public void accountNotFound() {
        CustomToast.showToast("Conta destino inválida!", context);
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