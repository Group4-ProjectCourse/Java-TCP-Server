package Data;


public class FirebaseService {
    private static FirebaseService firebaseService;



    private FirebaseService getInstance(){
        if (firebaseService == null){
            firebaseService = new FirebaseService();
        }
        return firebaseService;
    }
}
