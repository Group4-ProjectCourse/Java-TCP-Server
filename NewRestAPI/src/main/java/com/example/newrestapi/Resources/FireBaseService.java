package com.example.newrestapi.Resources;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class FireBaseService {
    FirebaseDatabase db;
    private static final String DATABASE_URL = "https://mysmarthouse-f0aa5-default-rtdb.europe-west1.firebasedatabase.app/";
    private static final String FILE_NAME = "myServiceAccount.json";


    public FireBaseService() throws IOException, FileNotFoundException {


        FileInputStream fis = new FileInputStream(FILE_NAME);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(fis))
                .setDatabaseUrl(DATABASE_URL)
                .build();

        FirebaseApp.initializeApp(options);

        db = FirebaseDatabase.getInstance();
    }

    public FirebaseDatabase getDb() {
        return db;
    }
}
