package com.example.vinicius.aula03.util;

import com.example.vinicius.aula03.entity.CloudantResponse;

import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Created by logonrm on 27/04/2017.
 * Como oganização, o RetroFit organiza todas as otas da sua API dentro de uma interface
 */

public interface CloudantRequestInterface {

    @GET("_all_docs?include_docs=true")
    Call<CloudantResponse> getAllDocs();
}

