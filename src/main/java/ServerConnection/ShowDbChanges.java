package ServerConnection;

import Data.DataBaseService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class ShowDbChanges implements Runnable {
    public void run() {
        DataBaseService fbs = null;
        fbs = new DataBaseService();

        DatabaseReference ref = fbs.getDb()
                .getReference("/");
        ref.addValueEventListener(new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot) {
                Object document = dataSnapshot.getValue();
                System.out.println("Updated values:");
                System.out.println(document);
            }

            public void onCancelled(DatabaseError error) {
                System.out.print("Error: " + error.getMessage());
            }
        });
    }
}
