package com.fiap.nac02;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                chamarCadastro();
            }
        },2000);
    }

    public void chamarCadastro(){
        Intent toCadastroCard = new Intent(this, CadastraEnderecoActivity.class);
        startActivity(toCadastroCard);
        finish();
    }

}
