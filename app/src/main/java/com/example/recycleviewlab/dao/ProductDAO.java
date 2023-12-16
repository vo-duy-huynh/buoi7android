package com.example.recycleviewlab.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.recycleviewlab.database.DBHelper;
import com.example.recycleviewlab.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    DBHelper dbHelper;
    public ProductDAO(Context context) {
        dbHelper = new DBHelper(context);
    }
    public List<Product> GetAll()
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<Product> listProduct = new ArrayList<>();
        String query = "SELECT * FROM product";
        Cursor c = db.rawQuery(query, null);
        while (c.moveToNext())
        {
            Product temp = new Product();
            temp.setId(c.getInt(0));
            temp.setName(c.getString(1));
            temp.setPrice(c.getFloat(2));
            temp.setImage(c.getString(3));
            listProduct.add(temp);
        }
        return listProduct;
    }
    public void Insert(Product p) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
// values.put("id", p.getId());
        values.put("name", p.getName());
        values.put("price", p.getPrice());
        values.put("image", p.getImage());
        db.insert("product", null, values);
    }
    public void Update(Product p) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", p.getName());
        values.put("price", p.getPrice());
        values.put("image", p.getImage());
        db.update("product", values, "id=?", new String[] { String.valueOf(p.getId()) });
    }
    public Product GetProductById(int productId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = "SELECT * FROM product WHERE id = " + productId;
        Cursor c = db.rawQuery(query, null);
        if (c.moveToFirst())
        {
            Product temp = new Product();
            temp.setId(c.getInt(0));
            temp.setName(c.getString(1));
            temp.setPrice(c.getFloat(2));
            temp.setImage(c.getString(3));
            return temp;
        }
        return null;
    }
    public void Delete(int productId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("product", "id=?", new String[] { String.valueOf(productId) });
    }
}