package Data;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

public class FirebaseServices {
    private FirebaseDatabase database;
    private DatabaseReference dref;
    private FirebaseConnection firebaseConnection;
    private Map<String, Object> data;

    public void updateLamp(String value){
        firebaseConnection.getInstance();
        dref = FirebaseDatabase.getInstance("https://smart-house-ae2d9-default-rtdb.europe-west1.firebasedatabase.app").getReference().child("Devices").child("Lamp").child("Ambient");
        data.put("LightSwitch", value);
        dref.updateChildren(data);
    }

}
