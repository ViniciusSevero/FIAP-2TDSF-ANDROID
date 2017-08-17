package br.com.fiap.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnStart = (Button) findViewById(R.id.btn_start_3);
    }

    public void startThirdActivity(View v){
        Intent i = new Intent(this, ThirdActivity.class);
        startActivity(i);
        // finish(); // Matar esse activity ao ir para a terceira
    }
}
