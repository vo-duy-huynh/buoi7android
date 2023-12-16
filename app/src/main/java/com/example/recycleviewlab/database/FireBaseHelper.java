package com.example.recycleviewlab.database;

import com.example.recycleviewlab.models.Product;
import com.google.firebase.firestore.FirebaseFirestore;
public class FireBaseHelper {
    private static FirebaseFirestore db;
    public static FirebaseFirestore getInstance() {
        if (db == null) {
            db = FirebaseFirestore.getInstance();
        }
        return db;
    }


}
