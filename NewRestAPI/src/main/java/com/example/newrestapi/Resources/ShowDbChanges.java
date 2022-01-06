package com.example.newrestapi.Resources;

import com.example.newrestapi.Model.SmartHouse;
import com.example.newrestapi.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ShowDbChanges implements Runnable {

    Map<String, Object> smartHouseMapDoors;
    Map<String, Object> smartHouseMapUsers;
    Map<String, Object> smartHouseMapWindows;
    Map<String, Object> smartHouseMapLights;
    Map<String, Object> smartHouseMap;

    public void run() {
       /* User user = new User(1, "Wael", "1234");
        User user1 = new User(2, "Thread", "4321");
        User[] users = {user, user1};
        SmartHouseResource smartHouseResource = new SmartHouseResource();
        SmartHouse smartHouse = null;
        smartHouse = new SmartHouse();
        try {
            smartHouse = smartHouseResource.readJsonFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        smartHouseMapUsers = new HashMap<>();
        smartHouseMapLights = new HashMap<>();
        smartHouseMapWindows = new HashMap<>();
        smartHouseMapDoors = new HashMap<>();
        smartHouseMap = new HashMap<>();
        System.out.println(smartHouseMapDoors);
        System.out.println(smartHouseMapLights);
        System.out.println(smartHouseMapWindows);
        System.out.println(smartHouseMapUsers);
        System.out.println(smartHouseMap);
        assert smartHouse != null;
        for (int i = 0; i < smartHouse.getDoors().length; i++) {
            System.out.println(smartHouse.getDoor(i));
            smartHouseMapDoors.put(i + "", smartHouse.getDoor(i));
        }
        for (int i = 0; i < smartHouse.getWindows().length; i++) {
            System.out.println(smartHouse.getWindow(i));
            smartHouseMapWindows.put(i + "", smartHouse.getWindow(i));
        }
        for (int i = 0; i < smartHouse.getLights().length; i++) {
            System.out.println(smartHouse.getLight(i));
            smartHouseMapLights.put(i + "", smartHouse.getLight(i));
        }
        for (int i = 0; i < users.length; i++) {
            System.out.println(users[i]);
            smartHouseMapUsers.put(users[i].getUserName(), users[i]);
        }
        smartHouseMap.put("temperature", smartHouse.getTemperature());
        smartHouseMap.put("humidity", smartHouse.getHumidity());
        System.out.println(smartHouseMapDoors);
        System.out.println(smartHouseMapLights);
        System.out.println(smartHouseMapWindows);
        System.out.println(smartHouseMapUsers);
        System.out.println(smartHouseMap);

        FireBaseService fbs = null;
        try {
            fbs = new FireBaseService();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert fbs != null;
        DatabaseReference ref = fbs.getDb()
                .getReference("/Users/");
        ref.setValueAsync(smartHouseMapUsers);

        for (int i = 0; i < users.length; i++) {
            ref = fbs.getDb()
                    .getReference("Users/" + users[i].getUserName() + "/smartHouse/Doors/");
            ref.setValueAsync(smartHouseMapDoors);
            ref = fbs.getDb()
                    .getReference("Users/" + users[i].getUserName() + "/smartHouse/Lights/");
            ref.setValueAsync(smartHouseMapLights);
            ref = fbs.getDb()
                    .getReference("Users/" + users[i].getUserName() + "/smartHouse/Windows/");
            ref.setValueAsync(smartHouseMapWindows);
            ref = fbs.getDb()
                    .getReference("Users/" + users[i].getUserName() + "/smartHouse/Temp");
            ref.setValueAsync(smartHouseMap);
        }*/
        FireBaseService fbs = null;
        try {
            fbs = new FireBaseService();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert fbs != null;
        DatabaseReference ref1 = fbs.getDb()
                .getReference("/");
        ref1.addValueEventListener(new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot) {
                Object document = dataSnapshot.getValue();
                System.out.println(document);
            }


            public void onCancelled(DatabaseError error) {
                System.out.print("Error: " + error.getMessage());
            }
        });

    }

   /* public static void main(String[] args) throws IOException, InterruptedException {
        while (true) {
            Thread thread = new Thread(new ShowDbChanges());
            thread.start();
            Thread.sleep(1000);
        }
    }*/
}
