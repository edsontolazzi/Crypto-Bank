package com.example.cryptobank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cryptobank.tools.CustomToast;

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
    private Button btReload = null;
    Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        context = this.getApplicationContext();

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
        this.btReload = (Button) findViewById(R.id.bt_reload);
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

        this.btReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarSaldoCliente();
            }
        });
    }

    private void abrirTelaDeposito() {
        Intent intent = new Intent(this, DepositActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString("jsonDadosCliente", this.jsonDadosCliente);

        intent.putExtras(bundle);
        this.startActivity(intent);
    }

    private void abrirTelaSaque() {
        Intent intent = new Intent(this, WithdrawActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString("jsonDadosCliente", this.jsonDadosCliente);
        bundle.putString("saldo", this.saldo);

        intent.putExtras(bundle);
        this.startActivity(intent);
    }

    private void abrirTelaTransferencia() {
        Intent intent = new Intent(this, TransferenceActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString("jsonDadosCliente", this.jsonDadosCliente);
        bundle.putString("saldo", this.saldo);

        intent.putExtras(bundle);
        this.startActivity(intent);
    }

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