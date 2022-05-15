package com.example.cryptobank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends AppCompatActivity {

    private String jsonDadosCliente = null;
    private String saldo = null;

    ImageView logo = null;
    private TextView tvBemVindo = null;
    private TextView tvSaldo = null;
    private Button btDeposito = null;
    private Button btSaque = null;
    private Button btTransferencia = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        receberParametros();
        initComponents();
        initListeners();

        exibeNomeCliente();
        buscarSaldoCliente();
    }

    private void receberParametros() {
        Bundle bundle = getIntent().getExtras();
        this.jsonDadosCliente = bundle.getString("jsonDadosCliente");
    }

    private void initComponents() {
        this.logo = findViewById(R.id.crypto_logo_home);
        this.logo.setImageResource(R.drawable.cryptomlogo);

        this.tvBemVindo = (TextView) findViewById(R.id.tv_bem_vindo);
        this.tvSaldo = (TextView) findViewById(R.id.tv_saldo);

        this.btDeposito = (Button) findViewById(R.id.bt_deposito);
        this.btSaque = (Button) findViewById(R.id.bt_saque);
        this.btTransferencia = (Button) findViewById(R.id.bt_transferencia);
    }

    private void initListeners() {
        this.btDeposito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirTelaDeposito();
            }
        });

        this.btSaque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirTelaSaque();
            }
        });

        this.btTransferencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirTelaTransferencia();
            }
        });
    }

    private void abrirTelaDeposito() { }
    private void abrirTelaSaque() { }
    private void abrirTelaTransferencia() { }

    private void exibeNomeCliente() {
        tvBemVindo.setText("Bem vindo(a), " + getAccountName() + "!");
    }

    private void buscarSaldoCliente() {
        (new API(getBaseContext(), getAccountNumber(), getToken())).getBalance(HomeActivity.this);
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

    private String getAccountName() {
        try {
            String jsonAccount = getJsonAccount();
            if (jsonAccount == null) {
                return null;
            }

            return new JSONObject(jsonAccount).getString("name");
        } catch (JSONException e) {
            Log.d("JSON_NAME", e.getMessage());
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

    public void setSaldo(String saldo) {
        this.saldo = saldo;
        tvSaldo.setText("R$ " + this.saldo);
    }
}