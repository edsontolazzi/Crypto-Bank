package com.example.cryptobank.src.domains.deposit.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.cryptobank.R;

public class DepositScreen extends AppCompatActivity {

    Button deposit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit_screen);
        this.initComponents();
        this.initListeners();
    }

    private void initComponents(){
        this.deposit = findViewById(R.id.bt_deposit);
    }

    private void initListeners(){
        this.deposit.setOnClickListener(v -> {
            this.finish();
        });
    }
}