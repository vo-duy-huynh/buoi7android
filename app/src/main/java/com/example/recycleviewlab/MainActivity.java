package com.example.recycleviewlab;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.recycleviewlab.adapters.ProductAdapter;
import com.example.recycleviewlab.dao.ProductDAO;
import com.example.recycleviewlab.models.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcvProduct;
    private ProductAdapter productAdapter;
    List<Product> productList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcvProduct=findViewById(R.id.recyclerview);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        rcvProduct.addItemDecoration(itemDecoration);
        productList=getData();
        productAdapter=new ProductAdapter();
        productAdapter.setData(productList);
        rcvProduct.setAdapter(productAdapter);
        rcvProduct.setLayoutManager(new LinearLayoutManager(this));

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddProductDialog();
            }
        });
    }
    private void showAddProductDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_product, null);
        builder.setView(dialogView);

        final EditText etProductId = dialogView.findViewById(R.id.et_product_id);
        final EditText etProductName = dialogView.findViewById(R.id.et_product_name);
        final EditText etProductPrice = dialogView.findViewById(R.id.et_product_price);
        final EditText etProductImage = dialogView.findViewById(R.id.et_product_img);

        builder.setPositiveButton("Add Product", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String productId = etProductId.getText().toString();
                String productName = etProductName.getText().toString();
                String productPrice = etProductPrice.getText().toString();
                String productImage = etProductImage.getText().toString();
                if (TextUtils.isEmpty(productId) || TextUtils.isEmpty(productName) ||
                        TextUtils.isEmpty(productPrice) || TextUtils.isEmpty(productImage)) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đầy đủ thông tin sản phẩm", Toast.LENGTH_SHORT).show();
                } else {
                    Product product = new Product();
                    product.setId(Integer.parseInt(productId));
                    product.setName(productName);
                    product.setPrice(Float.parseFloat(productPrice));
                    product.setImage(productImage);
                    ProductDAO productDAO = new ProductDAO(MainActivity.this);
                    productDAO.Insert(product);
                    productList.add(product);
                    productAdapter.setData(productList);
                    productAdapter.notifyDataSetChanged();
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    List<Product> getData() {
        List<Product> list = new ArrayList<>();
        ProductDAO productDAO = new ProductDAO(this);
        list = productDAO.GetAll();
        return list;
    }

}