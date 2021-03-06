package Data;

import Model.CallBack;
import Model.RegisterUser;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import org.apache.log4j.BasicConfigurator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;


/*

we can use a singleton to  allows many threads to easily share a global instance.
But we can change it to whatever pattern will suit us best
This class will be used for the database queries
Each method should handle a different query.



*/
public class DataBaseService {

    private DatabaseReference dbref;
    private FirebaseDatabase firebaseDatabase;
    private FileInputStream serviceAccount = null;
    FirebaseOptions options = null;
    private static Map<String, Object> data;


    public DataBaseService() {
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


    // trying to change the lightSwitch state


    public static void handleWriteToDatabase(String s) {
        final CountDownLatch done = new CountDownLatch(1);
        FirebaseDatabase.getInstance("https://smart-house-ae2d9-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Devices/Lamp/Ambient/LightSwitch").setValue(s, new DatabaseReference.CompletionListener() {
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                done.countDown();
            }
        });
        try {
            done.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public static void handleLightSwitch(String value) {
        DataBaseService fbs = null;
        fbs = new DataBaseService();

        DatabaseReference ref = fbs.getDb()
                .getReference("Devices/State");
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://smart-house-ae2d9-default-rtdb.europe-west1.firebasedatabase.app/");
//        DatabaseReference ref = database.getReference();
        System.out.println("reference " + ref);
//        DatabaseReference refs = ref.child("Sensors").child("humidity");
//        ref.child("Devices").child("Air-Condition").child("Modes").child("Day").setValue("13C");

        System.out.println(ref);
        data = new HashMap<String, Object>();
        data.put("LightSwitch", value);

//        refs.setValue();
        System.out.println(data);
        ref.updateChildren(data, new DatabaseReference.CompletionListener() {
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                System.out.println("complete" + databaseReference.push());
            }
        });

    }


    public static void handleDoorSwitch(String value) {
        DataBaseService fbs = null;
        fbs = new DataBaseService();

        DatabaseReference ref = fbs.getDb()
                .getReference("/Devices/State");
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://smart-house-ae2d9-default-rtdb.europe-west1.firebasedatabase.app/");
//        DatabaseReference ref = database.getReference();
        System.out.println("reference " + ref);
//        DatabaseReference refs = ref.child("Sensors").child("humidity");
//        ref.child("Devices").child("Air-Condition").child("Modes").child("Day").setValue("13C");

        System.out.println(ref);
        data = new HashMap<String, Object>();
        data.put("DoorSwitch", value);

//        refs.setValue();
        System.out.println(data);
        ref.updateChildren(data, new DatabaseReference.CompletionListener() {
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                System.out.println("complete" + databaseReference.push());
            }
        });

    }




    public static void handleWindowSwitch(String value) {
        DataBaseService fbs = null;
        fbs = new DataBaseService();

        DatabaseReference ref = fbs.getDb()
                .getReference("/Devices/State");
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://smart-house-ae2d9-default-rtdb.europe-west1.firebasedatabase.app/");
//        DatabaseReference ref = database.getReference();
        System.out.println("reference " + ref);
//        DatabaseReference refs = ref.child("Sensors").child("humidity");
//        ref.child("Devices").child("Air-Condition").child("Modes").child("Day").setValue("13C");

        System.out.println(ref);
        data = new HashMap<String, Object>();
        data.put("WindowSwitch", value);

//        refs.setValue();
        System.out.println(data);
        ref.updateChildren(data, new DatabaseReference.CompletionListener() {
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                System.out.println("complete" + databaseReference.push());
            }
        });

    }

    public static void handleTemperatureSensor(int value) {
        DataBaseService fbs = null;
        fbs = new DataBaseService();

        DatabaseReference ref = fbs.getDb()
                .getReference("/Devices/State");
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://smart-house-ae2d9-default-rtdb.europe-west1.firebasedatabase.app/");
//        DatabaseReference ref = database.getReference();
        System.out.println("reference " + ref);
//        DatabaseReference refs = ref.child("Sensors").child("humidity");
//        ref.child("Devices").child("Air-Condition").child("Modes").child("Day").setValue("13C");

        System.out.println(ref);
        data = new HashMap<String, Object>();
        data.put("Temperature", value);

//        refs.setValue();
        System.out.println(data);
        ref.updateChildren(data, new DatabaseReference.CompletionListener() {
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                System.out.println("complete" + databaseReference.push());
            }
        });

    }



    public static void handleHumiditySensor(int value) {
        DataBaseService fbs = null;
        fbs = new DataBaseService();

        DatabaseReference ref = fbs.getDb()
                .getReference("/Devices/State");
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://smart-house-ae2d9-default-rtdb.europe-west1.firebasedatabase.app/");
//        DatabaseReference ref = database.getReference();
        System.out.println("reference " + ref);
//        DatabaseReference refs = ref.child("Sensors").child("humidity");
//        ref.child("Devices").child("Air-Condition").child("Modes").child("Day").setValue("13C");

        System.out.println(ref);
        data = new HashMap<String, Object>();
        data.put("Humidity", value);

//        refs.setValue();
        System.out.println(data);
        ref.updateChildren(data, new DatabaseReference.CompletionListener() {
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                System.out.println("complete" + databaseReference.push());
            }
        });

    }



    private static String value;


     //this method reads from firebase the pin code value
   /* public static String handleUserPin(String uid) {
        DataBaseService fbs = null;
        fbs = new DataBaseService();
        DatabaseReference ref = fbs.getDb()
                .getReference("/Users");
        ref.child(uid)
                .addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // 3. Set the public variable in your class equal to value retrieved
                        value = dataSnapshot.getValue(String.class);
                        // 4a. EDIT: now that your fetch succeeded, you can trigger whatever else you need to run in your class that uses yourNameVariable, and you can be sure yourNameVariable is not null.


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }

                });

        return value;
    }*/

}






