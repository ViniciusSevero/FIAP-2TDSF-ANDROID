package fiap.com.br.swapi.util;

import fiap.com.br.swapi.entity.People;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by logonrm on 25/05/2017.
 */

public interface SwapiRequestInterface {
    @GET("1")
    Call<People> getPeople();
}
