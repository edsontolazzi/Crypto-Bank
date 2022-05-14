package com.example.cryptobank.src;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.cryptobank.src.domains.login.ui.LoginScreen;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = new Intent(MainActivity.this, LoginScreen.class);
        startActivity(i);
    }
}