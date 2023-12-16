package com.example.recycleviewlab.services;

import com.example.recycleviewlab.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ProductService {

//    public static final String BASE_URL = "http://10.0.2.2:3000/api/";
    public static final String BASE_URL = "https://657d6991853beeefdb9ab3ed.mockapi.io/api/";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    @GET("products")
    Call<List<Product>> getProducts();
}