package com.example.androidtcpconnection;

import android.os.Handler;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class DataBase {
    private DatabaseReference dbref;
    private FirebaseDatabase firebaseDatabase;
    Map<String, Object> data;

    public void UpdateLampElement(String value) {
        data = new HashMap<>();
        dbref = FirebaseDatabase
                .getInstance("https://smart-house-ae2d9-default-rtdb.europe-west1.firebasedatabase.app/")
                .getReference().child("Devices").child("Lamp").child("Ambient");

        data.put("LightSwitch", value);


        dbref.updateChildren(data);


    }

}