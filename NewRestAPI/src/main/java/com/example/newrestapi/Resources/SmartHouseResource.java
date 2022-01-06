package com.example.newrestapi.Resources;

import com.example.newrestapi.Model.*;
import com.google.gson.Gson;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.json.JSONException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;


@Path("/devices")
public class SmartHouseResource {
    Gson gson = new Gson();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllDevices() throws JSONException, IOException, InterruptedException {
        return gson.toJson(readJsonFile());
    }

    @GET
    @Path("{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getDevices(@PathParam("type") String type) throws JSONException, IOException {
        SmartHouse smartHouse = readJsonFile();
        switch (type) {
            case "doors":
                return Arrays.toString(smartHouse.getDoors());
            case "lights":
                return Arrays.toString(smartHouse.getLights());
            case "windows":
                return Arrays.toString(smartHouse.getWindows());
            case "temperature":
                return String.valueOf(smartHouse.getTemperature());
            case "humidity":
                return String.valueOf(smartHouse.getHumidity());
        }
        return gson.toJson(readJsonFile());
    }

    @GET
    @Path("{type}/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSpecDevice(@PathParam("type") String type, @PathParam("id") int id) throws JSONException, IOException {
        SmartHouse smartHouse = readJsonFile();
        Door[] doors = smartHouse.getDoors();
        Light[] lights = smartHouse.getLights();
        Window[] windows = smartHouse.getWindows();
        switch (type) {
            case "doors":
                for (int i = 0; i < doors.length; i++) {
                    if (doors[i].getDoorId() == id)
                        return doors[i].toString();
                }
            case "lights":
                for (int i = 0; i < lights.length; i++) {
                    if (lights[i].getLightId() == id)
                        return lights[i].toString();
                }
            case "windows":
                for (int i = 0; i < windows.length; i++) {
                    if (windows[i].getWindowId() == id)
                        return windows[i].toString();
                }
        }

        return null;
    }

    @PUT
    @Path("{type}/{id}/{value}")
    public void updateSpecDevice(@PathParam("type") String type, @PathParam("id") int id, @PathParam("value") boolean value) throws JSONException, IOException {
        SmartHouse smartHouse = readJsonFile();
        Door[] doors = smartHouse.getDoors();
        Light[] lights = smartHouse.getLights();
        Window[] windows = smartHouse.getWindows();
        switch (type) {
            case "doors":
                for (int i = 0; i < doors.length; i++) {
                    if (doors[i].getDoorId() == id) {
                        smartHouse.getDoor(i).setDoorStatus(value);
                        saveJsonFile(smartHouse);
                        break;
                    }
                }
                break;
            case "lights":
                for (int i = 0; i < lights.length; i++) {
                    if (lights[i].getLightId() == id) {
                        smartHouse.getLight(i).setLightStatus(value);
                        saveJsonFile(smartHouse);
                        break;
                    }
                }
                break;
            case "windows":
                for (int i = 0; i < windows.length; i++) {
                    if (windows[i].getWindowId() == id) {
                        smartHouse.getWindow(i).setWindowStatus(value);
                        saveJsonFile(smartHouse);
                        break;
                    }
                }
                break;
        }
        saveJsonFile(smartHouse);
    }


    @Path("temperature/{value}")
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    public void setTemp(@PathParam("value") int value) throws IOException, JSONException {
        SmartHouse smartHouse = readJsonFile();
        smartHouse.setTemperature(value);
        saveJsonFile(smartHouse);
    }


    @Path("humidity/{value}")
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    public void setHum(@PathParam("value") int value) throws IOException, JSONException {
        SmartHouse smartHouse = readJsonFile();
        smartHouse.setHumidity(value);
        saveJsonFile(smartHouse);
    }


    public void saveJsonFile(SmartHouse smartHouse) {
        gson = new Gson();
        String jsonString = gson.toJson(smartHouse);
        try {
            FileWriter writeToFile = new FileWriter("C:\\Users\\wael-\\IdeaProjects\\NewRestAPI\\src\\main\\java\\com\\example\\newrestapi\\smartHouse.json");
            writeToFile.write(jsonString);
            writeToFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(jsonString);
    }

    public SmartHouse readJsonFile() throws IOException {
        gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("C:\\Users\\wael-\\IdeaProjects\\NewRestAPI\\src\\main\\java\\com\\example\\newrestapi\\smartHouse.json"));
        // convert JSON string to User object
        SmartHouse smartHouse = gson.fromJson(reader, SmartHouse.class);
        // close reader
        reader.close();

        return smartHouse;
    }
}