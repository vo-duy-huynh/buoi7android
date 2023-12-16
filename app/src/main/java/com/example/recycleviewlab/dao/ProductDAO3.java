package com.example.recycleviewlab.dao;

import com.example.recycleviewlab.database.FireBaseHelper;
import com.example.recycleviewlab.models.Product;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ProductDAO3 {

    private static final String COLLECTION_NAME = "products";
    private static CollectionReference productCollection = FireBaseHelper.getInstance().collection(COLLECTION_NAME);

    public static Task<Void> updateProduct(Product product) {
        return productCollection.document(product.getId().toString()).set(product);
    }

    public static Task<Void> deleteProduct(Product product) {
        return productCollection.document(product.getId().toString()).delete();
    }

    public static Task<QuerySnapshot> getAllProducts() {
        return productCollection.get();
    }
    public static Task<DocumentSnapshot> getProductById(String productId) {
        return productCollection.document(productId).get();
    }

    public static void addProduct(Product product) {
        productCollection.add(product);

    }
}
