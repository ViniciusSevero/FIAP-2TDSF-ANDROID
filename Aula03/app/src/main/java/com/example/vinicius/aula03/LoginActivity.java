package com.example.vinicius.aula03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void logar(View v){
        Intent toNotasCard = new Intent(this, NotasCardActivity.class);
        startActivity(toNotasCard);
    }

    public void cadastrar(View v){
        Intent toCadastroCard = new Intent(this, CadastraLoginActivity.class);
        startActivity(toCadastroCard);
    }
}
