package com.example.logonpf.aulaintent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class PowerConnectionReceiver extends BroadcastReceiver {
    public PowerConnectionReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {
            Toast.makeText(context, "Caregadro conectado", Toast.LENGTH_SHORT).show();
        } else if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)) {
            Intent launhIntent = context.getPackageManager().getLaunchIntentForPackage("com.google.android.apps.maps");
            context.startActivity(launhIntent);
        } else if (intent.getAction().equals(Intent.ACTION_BATTERY_LOW)) {
            Toast.makeText(context, "Bateria descaregando", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Chamada", Toast.LENGTH_SHORT).show();
        }
    }
}
