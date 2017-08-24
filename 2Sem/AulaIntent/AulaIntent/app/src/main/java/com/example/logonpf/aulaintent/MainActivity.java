package com.example.logonpf.aulaintent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_activity)
    protected Button btnActivity;

    @BindView(R.id.btn_pick)
    protected Button btnPick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_activity)
    public void openActivity() {
        Intent i = new Intent(this, Main2Activity.class);
        startActivity(i);
    }

    @OnClick(R.id.btn_pick)
    public void openFiapLocation() {
//        float lat = -23.564197f;
//        float lon = -46.6548597f;
//        String location = String.format(Locale.ENGLISH, "geo:%f,%f", lat, lon);
//        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(location));

        Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:96410037"));
        startActivity(i);
    }
}
