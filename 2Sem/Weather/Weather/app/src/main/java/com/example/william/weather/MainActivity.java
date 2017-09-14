package com.example.william.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.result)
    protected Button mResult;

    @BindView(R.id.call_api)
    protected TextView mCallApi;

    private YahooWeather mService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit mRetrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl("https://query.yahooapis.com")
                .client(client)
                .build();

        mService = mRetrofit.create(YahooWeather.class);
    }
    @OnClick(R.id.result)
    public void callApi(){
        String query = "select * from weather.forecast where woeid=455827";

        Call<String> call = mService.getWeather(query,"json");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                mCallApi.setText(response.body());
                Log.d("gofc","Acertoo mizerávi");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }
}
