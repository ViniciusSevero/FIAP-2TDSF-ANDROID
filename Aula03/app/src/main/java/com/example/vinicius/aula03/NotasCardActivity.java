package com.example.vinicius.aula03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.vinicius.aula03.entity.CloudantResponse;
import com.example.vinicius.aula03.entity.Row;
import com.example.vinicius.aula03.util.CloudantRequestInterface;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NotasCardActivity extends AppCompatActivity {

    private ArrayList<Row> rows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas_card);
        caregaNotas();
    }

    private void caregaNotas() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://4a4dccf0-d1b6-41a4-8f09-3c20d7a9633d-bluemix.cloudant.com/fiap-notas/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CloudantRequestInterface api = retrofit
                .create(CloudantRequestInterface.class);

        api.getAllDocs().enqueue(new Callback<CloudantResponse>() {
            @Override
            public void onResponse(Call<CloudantResponse> call, Response<CloudantResponse> response) {

                CloudantResponse cloudantResponse = response.body();
                rows = new ArrayList<>(Arrays.asList(cloudantResponse.getRows()));
                for (Row item : rows) {
                    Log.i("Noa: ", item.getDoc().getAssunto());
                }
            }

            @Override
            public void onFailure(Call<CloudantResponse> call, Throwable t) {

            }
        });
    }

    public void voltar(View v){
        finish();
    }
}
