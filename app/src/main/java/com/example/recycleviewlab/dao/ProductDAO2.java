package com.example.recycleviewlab.dao;
import com.example.recycleviewlab.models.Product;
import com.example.recycleviewlab.services.ProductService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class ProductDAO2 {
    public interface ProductCallback {
        void onSuccess(List<Product> productList);
        void onFailure(Throwable t);
    }

    public void getProducts(final ProductCallback callback) {
        ProductService productService = ProductService.retrofit.create(ProductService.class);
        Call<List<Product>> call = productService.getProducts();

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    List<Product> productList = response.body();
                    callback.onSuccess(productList);
                } else {
                    callback.onFailure(new Exception("Failed to get products"));
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }
}
