package Data;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseConnection {

    private static FirebaseConnection firebaseConnection;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    public FirebaseConnection() {
    }

    public FirebaseConnection getInstance(){
        if (firebaseConnection == null){
            firebaseConnection = new FirebaseConnection();
        }
        return firebaseConnection;
    }

    public void InitializeFirebase() {

    try {
        FileInputStream serviceAccount =
                new FileInputStream("C:\\Users\\Robin\\OneDrive\\Dokumente\\HKR software development\\HT21\\Network applications\\smart-house-ae2d9-firebase-adminsdk-ripme-4cad973d9d.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://smart-house-ae2d9-default-rtdb.europe-west1.firebasedatabase.app")
                .build();

        FirebaseApp.initializeApp(options);
    }catch (IOException e){
        e.printStackTrace();
        System.out.println("IO exception");
    }
}



}
