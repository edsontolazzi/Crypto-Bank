package com.example.cryptobank.tools;

import android.content.Context;
import android.widget.Toast;

public class CustomToast {
    public static void showToast(String message, Context context) {
        CharSequence text = "Hello toast!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
