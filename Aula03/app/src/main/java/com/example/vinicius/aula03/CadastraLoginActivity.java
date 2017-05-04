package com.example.vinicius.aula03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.vinicius.aula03.util.ArquivoDB;

import java.util.AbstractQueue;
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
    private HashMap<String, String> mapDados;
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
        rgSexo = (RadioGroup) findViewById(R.id.rgSexo);

        arquivoDB = new ArquivoDB();

        mapDados = new HashMap<>();
    }

    private boolean capturaDados() {
        boolean dadosOK = false;
        String nome, sobrenome, nascimento, email, senha, sexo;
        nome = edtNome.getText().toString();
        sobrenome = edtSobrenome.getText().toString();
        nascimento = edtDtNascimento.getText().toString();
        email = edtEmail.getText().toString();
        senha = edtSenha.getText().toString();

        //pegar radio selecionado
        int sexoId = rgSexo.getCheckedRadioButtonId();
        RadioButton rbSexo = (RadioButton) findViewById(sexoId);

        if (Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                !TextUtils.isEmpty(nome) &&
                !TextUtils.isEmpty(nome) &&
                !TextUtils.isEmpty(nome) &&
                !TextUtils.isEmpty(nome) &&
                !TextUtils.isEmpty(nome) &&
                (sexoId != -1)) {
            dadosOK = true;
            sexo = rbSexo.getText().toString();

            mapDados.put("nome", nome);
            mapDados.put("sobrenome", sobrenome);
            mapDados.put("email", email);
            mapDados.put("nascimento", nascimento);
            mapDados.put("senha", senha);
            mapDados.put("sexo", sexo);
        } else {
            Toast.makeText(this, R.string.dados_nook, Toast.LENGTH_SHORT).show();
        }

        return dadosOK;
    }

    public void gravarChaves(View v){
        if(capturaDados()){
            arquivoDB.gravarCaves(this, SP, mapDados);
            Toast.makeText(this, R.string.dados_ok, Toast.LENGTH_SHORT).show();
        }
    }

    public void exluirCaves(View v){
        arquivoDB.excluirChaves(this, SP, mapDados);
        Toast.makeText(this, R.string.exclusao_ok, Toast.LENGTH_SHORT).show();
    }

    public boolean verificarChaves(View v){
        if(arquivoDB.verificarCHave(this,SP,"email") && arquivoDB.verificarCHave(this,SP,"senha")){
            Toast.makeText(this, R.string.dados_login_ok, Toast.LENGTH_SHORT).show();
            return true;
        }else{
            Toast.makeText(this, R.string.dados_login_no_ok, Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void carregarChaves(View v){
        if(verificarChaves(v)) {
            String sexo = arquivoDB.retornaValor(this, SP, "sexo");
            edtNome.setText(arquivoDB.retornaValor(this, SP, "nome"));
            edtSobrenome.setText(arquivoDB.retornaValor(this, SP, "sobrenome"));
            edtDtNascimento.setText(arquivoDB.retornaValor(this, SP, "nascimento"));
            edtEmail.setText(arquivoDB.retornaValor(this, SP, "email"));
            edtSenha.setText(arquivoDB.retornaValor(this, SP, "senha"));

            if(sexo.equals("Feminino")){
                rgSexo.check(R.id.rbFeminino);
            }else{
                rgSexo.check(R.id.rbMasculino);
            }
        }
    }

    public void voltar() {
        finish();
    }

    public void gravarArquivo(View v){
        if(capturaDados()){
            try{
                arquivoDB.gravarArquivo(this, ARQ,mapDados.toString());
                Toast.makeText(this, R.string.arquivo_ok, Toast.LENGTH_SHORT).show();
            }catch (Exception e){

            }
        }
    }

    public void lerArquivo(View v){
        String txt = "Vazio!";

        try{
            txt = arquivoDB.lerArquivo(this, ARQ);
        }catch (Exception e){
            e.printStackTrace();
        }

        Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();
    }

    public void excluirArquivo(View v){
        try{
            arquivoDB.excluirArquivo(this, ARQ);
            Toast.makeText(this, R.string.arquivo_excluido, Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
