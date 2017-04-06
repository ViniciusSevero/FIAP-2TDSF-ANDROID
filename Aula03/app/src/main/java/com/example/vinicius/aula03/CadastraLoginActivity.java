package com.example.vinicius.aula03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.vinicius.aula03.util.ArquivoDB;

import java.util.HashMap;
import java.util.regex.Pattern;

public class CadastraLoginActivity extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtEmail;
    private EditText edtSenha;
    private EditText edtSobrenome;
    private EditText edtDtNascimento;
    private RadioGroup rgSexo;
    private ArquivoDB arquivoDB;
    private HashMap<String,String> mapDados;
    private final String ARQ = "dados.txt";
    private final String SP = "dados"; //Shared Preferences

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_login);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        edtSobrenome = (EditText) findViewById(R.id.edtSobrenome);
        edtDtNascimento = (EditText) findViewById(R.id.edtDtNascimento);
        rgSexo = (RadioGroup)  findViewById(R.id.rgSexo);

        arquivoDB = new ArquivoDB();

        mapDados = new HashMap<>();
    }

    private boolean capturaDados(){
        boolean dadosOK = false;
        String nome,sobrenome,nascimento,email,senha,sexo;
        nome = edtNome.getText().toString();
        sobrenome = edtSobrenome.getText().toString();
        nascimento = edtDtNascimento.getText().toString();
        email = edtEmail.getText().toString();
        senha = edtSenha.getText().toString();

        //pegar radio selecionado
        int sexoId = rgSexo.getCheckedRadioButtonId();
        RadioButton rbSexo = (RadioButton) findViewById(sexoId);

        if(Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                !TextUtils.isEmpty(nome) &&
                !TextUtils.isEmpty(nome) &&
                !TextUtils.isEmpty(nome) &&
                !TextUtils.isEmpty(nome) &&
                !TextUtils.isEmpty(nome) &&
                (sexoId != -1)){
            dadosOK = true;
            sexo = rbSexo.getText().toString();

            mapDados.put("nome",nome);
            mapDados.put("sobrenome",sobrenome);
            mapDados.put("email",email);
            mapDados.put("nascimento",nascimento);
            mapDados.put("senha",senha);
            mapDados.put("sexo",sexo);
        }else{
            Toast.makeText(this, R.string.dados_nook, Toast.LENGTH_SHORT).show();
        }

        return dadosOK;
    }
}
