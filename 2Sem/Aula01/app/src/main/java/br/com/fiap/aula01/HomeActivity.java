package br.com.fiap.aula01;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private TextView mTextView;
    private TextView mButton;
    private RelativeLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mTextView = (TextView) findViewById(R.id.txtHello);
        mButton = (Button) findViewById(R.id.btnPapada);
        mLayout = (RelativeLayout) findViewById(R.id.activity_home);

        mButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mLayout.setBackgroundColor(Color.YELLOW);
            }
        });

        mTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mButton.performClick();
            }
        });
    }
}
