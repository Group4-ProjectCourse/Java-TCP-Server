package com.example.newrestapi.Resources;

import com.example.newrestapi.Model.SmartHouse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SaveToDB {
    static SmartHouseResource smartHouseResource = new SmartHouseResource();
    private final String userAndPassword = "root";
    /**
     */
/*    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        SmartHouse smartHouse = smartHouseResource.readJsonFile();
        SaveToDB saveToDB = new SaveToDB();
       saveToDB.updateTempInDB(smartHouse,1);

    }*/


    public void updateTempInDB(SmartHouse smartHouse, int userID) {
        try {
            ObjectMapper objMap = new ObjectMapper();
            Connection con = null;
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthouse", userAndPassword, userAndPassword);
            PreparedStatement pstmt = con.prepareStatement("UPDATE smarthouse SET humidity = ?,temp = ? WHERE user_idUser = ?;");
            pstmt.setObject(1, objMap.writerWithDefaultPrettyPrinter().writeValueAsString(smartHouse.getHumidity()));
            pstmt.setObject(2, objMap.writerWithDefaultPrettyPrinter().writeValueAsString(smartHouse.getTemperature()));
            pstmt.setObject(3, objMap.writerWithDefaultPrettyPrinter().writeValueAsString(userID));
            pstmt.execute();
            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
