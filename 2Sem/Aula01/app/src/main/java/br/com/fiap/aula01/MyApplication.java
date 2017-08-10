package br.com.fiap.aula01;

import android.app.Application;

import retrofit2.Retrofit;

/**
 * Created by logonrm on 10/08/2017.
 */

public class MyApplication extends Application {

    public static Retrofit sRetrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        sRetrofit = new Retrofit.Builder()
                            .baseUrl("https://api.github.com/")
                            .build();
    }
}
