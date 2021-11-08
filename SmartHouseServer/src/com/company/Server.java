package com.company;

import com.company.Model.*;
import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class Server {
    ServerSocket serverSocket = null;
    SmartHouse smartHouse = new SmartHouse( );

    public Server() {
    }

    public void connect() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(1234);
        } catch (IOException e) {
            System.out.println("Could not listen on port: 1234");
            System.exit(-1);
        }
        Socket clientSocket;
        while (!serverSocket.isClosed()) {
            try {
                clientSocket = serverSocket.accept();
                clientSocket.setKeepAlive(true);
                System.out.println(clientSocket.getInetAddress() + " has been connected ! ");
                BufferedWriter out = null;
                BufferedReader in = null;
                try {
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                } catch (IOException ioe) {
                    System.out.println("Failed in creating streams");
                    System.exit(-1);
                }
                String inputLine, outputLine;
                try {
                    while ((inputLine = in.readLine()) != null) {
                        if (inputLine.equalsIgnoreCase("lightoff")) {
                            smartHouse.setLight(new Light(1, false));
                            editJsonFile(smartHouse);
                            out.write("Light is OFF");
                            out.flush();
                            break;
                        } else if (inputLine.equalsIgnoreCase("lighton")) {
                            smartHouse.setLight(new Light(1, true));
                            editJsonFile(smartHouse);
                            out.write("Light is ON");
                            out.flush();
                            break;
                        } else if (inputLine.equalsIgnoreCase("dooropen")) {
                            smartHouse.setDoor(new Door(1, true));
                            editJsonFile(smartHouse);
                            out.write("Door is OPEN");
                            out.flush();
                            break;
                        } else if (inputLine.equalsIgnoreCase("doorclosed")) {
                            smartHouse.setDoor(new Door(1, false));
                            editJsonFile(smartHouse);
                            out.write("Door is CLOSED");
                            out.flush();
                            break;
                        } else if (inputLine.equalsIgnoreCase("windowopen")) {
                            smartHouse.setWindow(new Window());
                            editJsonFile(smartHouse);
                            out.write("Window is OPEN");
                            out.flush();
                            break;
                        } else if (inputLine.equalsIgnoreCase("windowclosed")) {
                            smartHouse.setWindow(new Window());
                            editJsonFile(smartHouse);
                            out.write("Window is CLOSED");
                            out.flush();
                            break;
                        } else if (inputLine.equalsIgnoreCase("Bye")) {
                            clientSocket.close();
                            break;
                        } else if (inputLine.equalsIgnoreCase("Connected")) {
                            String tempC = in.readLine();
                            String tempF = in.readLine();
                            System.out.println("Temp now in C : " + tempC);
                            System.out.println("Temp now in F: " + tempF);
                            Temp temp = new Temp(Float.parseFloat(tempC), Float.parseFloat(tempF));
                            smartHouse.setcTemp(temp);
                            if (!smartHouse.getLight().isLightStatus()) {
                                out.write(0);
                                out.flush();
                            }
                            if (smartHouse.getLight().isLightStatus()) {
                                out.write(1);
                                out.flush();
                            }
                            if (smartHouse.getDoor().isDoorStatus()) {
                                out.write(1);
                                out.flush();
                            }
                            if (!smartHouse.getDoor().isDoorStatus()) {
                                out.write(0);
                                out.flush();
                            }
                            clientSocket.close();
                            break;
                        } else {
                            char[] chars = {'m', 'n', 'b'};
                            out.write(chars);
                            out.close();
                            clientSocket.close();
//                            System.out.println(inputLine);
//                            out.write(inputLine);
//                            out.flush();
                        }
                    }
                } catch (IOException ioe) {
                    System.out.println("Failed in reading, writing");
                    System.exit(-1);
                }
                try {
                    clientSocket.close();
                    out.close();
                    in.close();
                } catch (IOException ioe) {
                    System.out.println("Failed in closing down");
                    System.exit(-1);
                }
            } catch (IOException e) {
                System.out.println("Accept failed:1234");
                System.exit(-1);
            }
        }
    }

    public void editJsonFile(SmartHouse smartHouse) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(smartHouse);
        try {
            FileWriter writeTofile = new FileWriter("smartHouse.json");
            writeTofile.write(jsonString);
            writeTofile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(jsonString);
    }
}
