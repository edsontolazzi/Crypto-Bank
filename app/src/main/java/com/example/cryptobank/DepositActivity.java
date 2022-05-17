package com.example.cryptobank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class DepositActivity extends AppCompatActivity {

    ImageView logo = null;
    EditText valor = null;
    Button btDeposito = null;
    Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit2);

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
        valor = findViewById(R.id.text_valor);

        //Button's
        btDeposito = findViewById(R.id.bt_depositar);
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
            @Override
            public void onClick(View view) {

            }
        });
    }
}