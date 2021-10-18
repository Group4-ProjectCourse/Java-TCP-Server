package Data;


import com.google.firebase.database.FirebaseDatabase;

public class FirebaseService {
    private static FirebaseService firebaseService;
    FirebaseDatabase firebaseDatabase;


    private FirebaseService getInstance(){
        if (firebaseService == null){
            firebaseService = new FirebaseService();
        }
        return firebaseService;
    }
}
