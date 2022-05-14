package com.example.cryptobank.units.tools;

import android.content.Context;
import android.widget.Toast;

public class ToastComponent {
    public static void show(String message, Context context) {
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }
}
