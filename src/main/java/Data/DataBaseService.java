package Data;

import Model.RegisterUser;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.apache.log4j.BasicConfigurator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
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
    FirebaseOptions options= null;
    private  static Map<String, Object> data;


    public DataBaseService() {
        try {
            serviceAccount = new FileInputStream("C:\\Users\\yeahm\\Desktop\\Java TCP Server Application\\Java-TCP-Server\\src\\main\\java\\smart_house.json");
            options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://smart-house-ae2d9-default-rtdb.europe-west1.firebasedatabase.app/")
                    .build();
            BasicConfigurator.configure();
            FirebaseApp.initializeApp(options);
        } catch (
                FileNotFoundException e) {
            System.out.println("ERROR: invalid service account credentials. See README.");
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }


    }

    public  static  void writeToDatabase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://smart-house-ae2d9-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference ref = database.getReference();
        System.out.println("referemce " + ref);
        DatabaseReference refs = ref.child("Devices").child("Lamp").child("Ambient");

        System.out.println(refs);
        data = new HashMap<String, Object>();
        data.put("LightSwitch","LIGHT");

        System.out.println(data);
        refs.updateChildren(data, new DatabaseReference.CompletionListener() {
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                System.out.println("complete" + databaseReference.push());
            }
        });




    }

    // another method to write to firebase

    // trying to change the lightSwitch state


public  void handleWriteToDatabase() {
    final CountDownLatch done = new CountDownLatch(1);
        FirebaseDatabase.getInstance().getReference("Devices/Lamp/Ambient/LightSwitch").setValue("DARK", new DatabaseReference.CompletionListener() {
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

}

