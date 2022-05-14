package com.example.cryptobank.src.domains.login.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.cryptobank.R;

public class View extends AppCompatActivity {

    Context ctx = null;
    EditText numAccount = null;
    EditText password = null;
    Button btEntrar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        initComponents();
    }

    private void initComponents() {
        this.ctx = getApplicationContext();
        this.numAccount = findViewById(R.id.text_num_conta);
        this.password = findViewById(R.id.text_senha);
        this.btEntrar = findViewById(R.id.bt_entrar);
    }
}