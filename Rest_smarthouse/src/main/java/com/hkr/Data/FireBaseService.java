package com.hkr.Data;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import com.hkr.Data.Model.CallBack;
import com.hkr.Data.Model.CallBackName;
import com.hkr.Data.Model.Device;

import com.hkr.Data.Model.SmartHouse;
import org.apache.log4j.BasicConfigurator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class FireBaseService {


    private DatabaseReference dbref;
    private FirebaseDatabase firebaseDatabase;
    private FileInputStream serviceAccount = null;
    FirebaseOptions options = null;
    private static Map<String, Object> data;
    private static String value;
    public static Device device;
    private static List<Map<String, Object>> mylist;
   public static SmartHouse smartHouse = new SmartHouse();

    public FireBaseService() {
        try {
            serviceAccount = new FileInputStream("C:\\Users\\yeahm\\Downloads\\folder\\Key.json");
            options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://smart-house-ae2d9-default-rtdb.europe-west1.firebasedatabase.app/")
                    .build();

            BasicConfigurator.configure();
            FirebaseApp firebaseApp = null;
            List<FirebaseApp> firebaseApps = FirebaseApp.getApps();
            if (firebaseApps != null && !firebaseApps.isEmpty()) {
                for (FirebaseApp app : firebaseApps) {
                    if (app.getName().equals(FirebaseApp.DEFAULT_APP_NAME))
                        firebaseApp = app;
                }
            } else {
                firebaseApp.initializeApp(options);
            }
//            FirebaseApp.initializeApp(options);
            firebaseDatabase = FirebaseDatabase.getInstance();

        } catch (
                FileNotFoundException e) {
            System.out.println("ERROR: invalid service account credentials. See README.");
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }


    }

    public FirebaseDatabase getDb() {
        return firebaseDatabase;
    }


    //        refs.setValue();
    public static void getAllDevices(CallBack callback) {
        FireBaseService fbs = null;
        fbs = new FireBaseService();
        device = new Device();
        mylist = new ArrayList<Map<String, Object>>();
        DatabaseReference ref = fbs.getDb()
                .getReference("/Devices/State");
        Map<String, Object> chemainChild = new HashMap<>();
        // chemainChild.put("server/user/",array);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                callback.onCallBack(dataSnapshot.getValue(Device.class));



            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//mylist.add(device);

    }


    public static void getDeviceByName(CallBackName callBackName) {
        FireBaseService fbs = null;
        fbs = new FireBaseService();
        device = new Device();
        mylist = new ArrayList<Map<String, Object>>();
        DatabaseReference ref = fbs.getDb()
                .getReference("/Devices/State");
        Map<String, Object> chemainChild = new HashMap<>();
        // chemainChild.put("server/user/",array);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //   value = dataSnapshot.getValue(String.class);
                //   device =  dataSnapshot.getValue(DeviceTest.class);

                callBackName.callbackName(dataSnapshot.getValue(Device.class));


            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//mylist.add(device);

    }


    public static void deleteDevice(String key) {
        FireBaseService fbs = null;
        fbs = new FireBaseService();
        device = new Device();
        mylist = new ArrayList<Map<String, Object>>();
        DatabaseReference ref = fbs.getDb()
                .getReference("/Devices/State");
        Map<String, Object> chemainChild = new HashMap<>();
        // chemainChild.put("server/user/",array);

        data = new HashMap<String, Object>();
        data.put(key, value);

//        refs.setValue();
        System.out.println(data);
        ref.child(key).removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                System.out.println("succes!" +  databaseReference.push());
            }
        });
        {

        }
    }

            // this is for String values in firebase
    public static void updateDevice(String key, String value) {
        FireBaseService fbs = null;
        fbs = new FireBaseService();
        device = new Device();
        mylist = new ArrayList<Map<String, Object>>();
        DatabaseReference ref = fbs.getDb()
                .getReference("/Devices/State");
        Map<String, Object> chemainChild = new HashMap<>();
        // chemainChild.put("server/user/",array);

        data = new HashMap<String, Object>();
        data.put(key, value);

//        refs.setValue();
        System.out.println(data);
        ref.child(key).setValue(value, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                System.out.println("succes!" +  databaseReference.push());

            }
        });


    }

    // this is for int values in firebase
    public static void updateDeviceInt(String key, int value) {
        FireBaseService fbs = null;
        fbs = new FireBaseService();
        device = new Device();
        mylist = new ArrayList<Map<String, Object>>();
        DatabaseReference ref = fbs.getDb()
                .getReference("/Devices/State");
        Map<String, Object> chemainChild = new HashMap<>();
        // chemainChild.put("server/user/",array);

        data = new HashMap<String, Object>();
        data.put(key, value);

//        refs.setValue();
        System.out.println(data);
        ref.child(key).setValue(value, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                System.out.println("succes!" +  databaseReference.push());

            }
        });


    }
}
