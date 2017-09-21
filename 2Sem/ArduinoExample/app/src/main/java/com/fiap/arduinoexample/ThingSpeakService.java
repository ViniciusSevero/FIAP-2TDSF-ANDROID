package com.fiap.arduinoexample;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by logonrm on 21/09/2017.
 */

public interface ThingSpeakService {
    @GET("channels/329591/feeds.json")
    Call<ArduinoResponse> getArduinoResponse(@Query("api_key") String apiKey,
                                             @Query("results") int limit);
}
