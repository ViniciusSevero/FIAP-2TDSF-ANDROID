package com.fiap.arduinoexample;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.result)
    protected TextView mResult;

    private ThingSpeakService service;
    private String API_KEY = "S8FG69HBFBW999QH";
    private int API_RESULTS = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        final Retrofit mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.thingspeak.com")
                .build();

        service = mRetrofit.create(ThingSpeakService.class);

        Call<ArduinoResponse> call = service.getArduinoResponse(API_KEY,API_RESULTS);

        call.enqueue(new Callback<ArduinoResponse>() {
            @Override
            public void onResponse(Call<ArduinoResponse> call, Response<ArduinoResponse> response) {
                List<ArduinoResponse.ArduinoFeed> feeds = response.body().getFeeds();
                String result = feeds.size() == 0 ? "Temperatura não encontrada" : feeds.get(feeds.size() - 1).getField1();
                mResult.setText(response.body().getChannel().getField1() + " - " + result);
        }

            @Override
            public void onFailure(Call<ArduinoResponse> call, Throwable t) {

            }
        });
    }
}
