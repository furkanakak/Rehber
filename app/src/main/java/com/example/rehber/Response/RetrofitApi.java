package com.example.rehber.Response;

import com.example.rehber.Model.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApi {

    @GET("users")
    Call<List<Model>> getUsers();

}
