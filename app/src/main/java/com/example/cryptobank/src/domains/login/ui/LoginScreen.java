package com.example.cryptobank.src.domains.login.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cryptobank.R;
import com.example.cryptobank.src.domains.menu.ui.MenuScreen;

public class LoginScreen extends AppCompatActivity {

    Context ctx = null;
    EditText numAccount = null;
    EditText password = null;
    Button btEntrar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        initComponents();
        initListeners();
    }

    private void initComponents() {
        this.ctx = getApplicationContext();
        this.numAccount = findViewById(R.id.text_num_conta);
        this.password = findViewById(R.id.text_senha);
        this.btEntrar = findViewById(R.id.bt_entrar);
    }

    private void initListeners() {
        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginScreen.this, MenuScreen.class);
                startActivity(i);
            }
        });
    }
}