package com.example.cryptobank.src.domains.menu.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cryptobank.R;
import com.example.cryptobank.src.domains.deposit.ui.DepositScreen;

public class MenuScreen extends AppCompatActivity {

    Button deposit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);

        this.initComponents();
        this.initListeners();
    }

    private void initComponents () {
        this.deposit = findViewById(R.id.btDeposito);
    }

    private void initListeners() {
        deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuScreen.this, DepositScreen.class);
                startActivity(i);
            }
        });
    }
}