package com.example.logonrm.exerciciorestaurante;

import android.app.Activity;
import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.os.Bundle;
import android.renderscript.Double2;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends Activity {

    private EditText edtPeso, edtQtde, edtPagamento;
    private RadioGroup rdGroupBebida, rdGroupPagamento;
    private TextView txtTotal,txtTroco;
    private CheckBox chkClienteFiel;
    private double precoKG = 50;
    private double precoRefri = 4.50;
    private double precoSuco = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.edtPeso = (EditText) findViewById(R.id.edtPeso);
        this.edtQtde = (EditText) findViewById(R.id.edtQuantidade);
        this.edtPagamento = (EditText) findViewById(R.id.edtQPagamento);
        this.rdGroupBebida = (RadioGroup) findViewById(R.id.btgBebida);
        this.rdGroupPagamento = (RadioGroup) findViewById(R.id.btgPagamento);
        this.chkClienteFiel = (CheckBox) findViewById(R.id.chkClienteFiel);
        this.txtTotal = (TextView) findViewById(R.id.total);
        this.txtTroco = (TextView) findViewById(R.id.lblTroco);

    }

    public void calcular(View v){
        try {
            double peso = Double.parseDouble(this.edtPeso.getText().toString());
            int qtde = Integer.parseInt(this.edtQtde.getText().toString());
            int bebidaID = this.rdGroupBebida.getCheckedRadioButtonId();
            int pagamentoID = this.rdGroupPagamento.getCheckedRadioButtonId();
            boolean isClienteFiel = this.chkClienteFiel.isChecked();

            double precoPrato = peso / 1000 * precoKG;
            double precoBebida = 0;

            if(bebidaID == -1)
                throw new Exception("Selecione uma opção e bebida");

            if(pagamentoID == -1)
                throw new Exception("Selecione uma foma de pagamento");

            RadioButton rbBebida = (RadioButton) findViewById(bebidaID);
            RadioButton rbPagaemnto = (RadioButton) findViewById(pagamentoID);

            if(rbBebida.getText().toString().equals("Refrigerante")){
                precoBebida = Integer.parseInt(edtQtde.getText().toString()) * precoRefri;
            }else{
                precoBebida = Integer.parseInt(edtQtde.getText().toString()) * precoSuco;
            }

            double total = precoPrato + precoBebida;
            if(isClienteFiel)
                total = total * 0.95;

            this.txtTotal.setText(String.format(getString(R.string.vlFormatted), total));


            if (rbPagaemnto.getText().toString().equals("Dinheiro")) {
                if(!TextUtils.isEmpty(this.edtPagamento.getText().toString())){
                    double pagamento = Double.parseDouble(this.edtPagamento.getText().toString());
                    if(pagamento >= total){
                        txtTroco.setText(String.format(getString(R.string.vlFormatted), new Double(pagamento - total)));
                    }else{
                        throw new Exception("Valor de pagamento insuficiente");
                    }
                }else{
                   throw new Exception("Entre com um valor no pagamento");
                }
            } else {
                if(!TextUtils.isEmpty(this.edtPagamento.getText().toString())) {
                    throw new Exception("Opção inválida");
                }
            }


        }catch (NumberFormatException ne){
            Toast.makeText(this, "Digite um valor válido", Toast.LENGTH_SHORT).show();
            ne.printStackTrace();
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void limpar(View v){
        this.edtPeso.setText("");
        this.edtQtde.setText("");
        this.edtPagamento.setText("");
        this.rdGroupBebida.clearCheck();
        this.rdGroupPagamento.clearCheck();
        this.chkClienteFiel.setChecked(false);
        this.txtTotal.setText("");
        this.txtTroco.setText("");
    }
}
