package com.fiap.nac02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.HashMap;

public class CadastraEnderecoActivity extends AppCompatActivity {

    private EditText edtCidade, edtBairro, edtEndereco, edtComplemento;
    private RadioGroup rgEndereco;
    private RadioButton rbResidencial,rbComercial;
    private HashMap<String, String> map;
    private ArquivoB db;
    private static final String SP = "cadastro";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_endereco);

        this.map = new HashMap<>();
        this.db = new ArquivoB();

        this.edtCidade = (EditText) findViewById(R.id.edtCidade);
        this.edtBairro = (EditText) findViewById(R.id.edtBairro);
        this.edtEndereco = (EditText) findViewById(R.id.edtEndereco);
        this.edtComplemento = (EditText) findViewById(R.id.edtComplemento);
        this.rgEndereco = (RadioGroup) findViewById(R.id.rgEndereco);
        this.rbComercial = (RadioButton) findViewById(R.id.rbComercial);
        this.rbResidencial = (RadioButton) findViewById(R.id.rbResidencial);

    }

    @Override
    protected void onResume() {
        super.onResume();
        caregarEndereco();
    }

    public boolean capturarDados(){
        int selectedID = this.rgEndereco.getCheckedRadioButtonId();
        String cidade = this.edtCidade.getText().toString();
        String bairro = this.edtBairro.getText().toString();
        String complemento = this.edtComplemento.getText().toString();
        String endereco = this.edtEndereco.getText().toString();

        if(!TextUtils.isEmpty(cidade) &&
                !TextUtils.isEmpty(bairro) &&
                !TextUtils.isEmpty(complemento) &&
                !TextUtils.isEmpty(endereco) &&
                selectedID != -1){

            this.map.put("cidade",cidade);
            this.map.put("bairro",bairro);
            this.map.put("complemento",complemento);
            this.map.put("endereco",endereco);
            this.map.put("tipoEndereco",((RadioButton) findViewById(selectedID)).getText().toString());

            return true;
        }else{
            Toast.makeText(this, "Por favor, cadastre todas as informações!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void cadastrarEndereco(View v){
        if(capturarDados()) {
            Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
            this.db.gravarChaves(this, SP, this.map);
        }
    }

    public void caregarEndereco(){
        if(this.db.verificarChave(this,SP,"cidade") &&
                this.db.verificarChave(this,SP,"bairro") &&
                this.db.verificarChave(this,SP,"complemento") &&
                this.db.verificarChave(this,SP,"endereco") &&
                this.db.verificarChave(this,SP,"tipoEndereco")) {

            this.edtCidade.setText(this.db.retornaValor(this, SP, "cidade"));
            this.edtBairro.setText(this.db.retornaValor(this, SP, "bairro"));
            this.edtEndereco.setText(this.db.retornaValor(this, SP, "endereco"));
            this.edtComplemento.setText(this.db.retornaValor(this, SP, "complemento"));

            String tipoEndereco = this.db.retornaValor(this, SP, "tipoEndereco");
            this.rgEndereco.clearCheck();

            if (tipoEndereco.equals("Residencial")) {
                this.rbResidencial.setChecked(true);
            } else {
                this.rbComercial.setChecked(true);
            }
        }
    }
}
