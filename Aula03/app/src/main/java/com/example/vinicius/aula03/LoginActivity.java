package com.example.vinicius.aula03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vinicius.aula03.util.ArquivoDB;

public class LoginActivity extends AppCompatActivity {

    EditText edtEmail, edtSenha;
    private ArquivoDB arquivoDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        arquivoDB = new ArquivoDB();

        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
    }

    @Override
    protected void onResume() {
        this.edtSenha.setText("");
    }

    public void logar(View v){
        if(validarDadosLoin(this.edtEmail.getText().toString(), this.edtSenha.getText().toString())) {

            String email = arquivoDB.retornaValor(this, "dados", "email");
            String senha = arquivoDB.retornaValor(this, "dados", "senha");

            Toast.makeText(this, "Preencha corretamente os dados do login", Toast.LENGTH_SHORT).show();

            if(email.equals(this.edtEmail.getText().toString()) && senha.equals(this.edtSenha.getText().toString())){
                Intent toNotasCard = new Intent(this, NotasCardActivity.class);
                startActivity(toNotasCard);
            }else{
                Toast.makeText(this, "Usuário ou senha inválidos", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Preencha corretamente os dados do login", Toast.LENGTH_SHORT).show();
        }
    }

    public void cadastrar(View v){
        Intent toCadastroCard = new Intent(this, CadastraLoginActivity.class);
        startActivity(toCadastroCard);
    }

    public boolean validarDadosLoin(String email, String senha){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && !TextUtils.isEmpty(senha);
    }
}
