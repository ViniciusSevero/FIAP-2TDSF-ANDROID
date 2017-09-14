package com.example.william.weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

/**
 * Created by logonrm on 14/09/2017.
 */

public interface YahooWeather {

    @GET("v1/public/yql")
    Call<String> getWeather(@Query("q") String query,
                            @Query("format") String format);

}
