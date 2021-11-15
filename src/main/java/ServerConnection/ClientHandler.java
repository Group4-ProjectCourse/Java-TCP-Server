package ServerConnection;

import Data.DataBaseService;
import Model.*;
import com.google.gson.Gson;

import javax.xml.stream.FactoryConfigurationError;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Objects;



public class ClientHandler implements Runnable {

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private boolean end = false;
    SmartHouse smartHouse = new SmartHouse();


    BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter key = new BufferedWriter(new OutputStreamWriter(System.out));


    private static DataBaseService dataBaseService;


    public ClientHandler(Socket clientHandler) throws IOException {
        this.client = clientHandler;
        this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        this.out = new PrintWriter(client.getOutputStream(), true);


    }


    // This class will handle the client request, such as the database request
    // for now it will just send the host address


    // this variable pin reads from the firebase the password
   // String pin =  DataBaseService.handleUserPin("Pin");
    public void run() {


        String stringData = null;
        try {
            stringData = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {

            while (!end) {

// we read the client response  here.
                assert stringData != null;
                //   System.out.println("clientResponse " + stringData.toLowerCase());
                //dataBaseService.testWriteToDatabase("LIGHT");
                // here we should get the client response such as value change (LAMP= DARK or LAMP = LIGHT)
                //   System.out.println("Here is the pin " + DataBaseService.handleUserPin());
                // passingValue("Pin");


                if (stringData.equalsIgnoreCase("lightoff")) {
                    smartHouse.setLight(new Light(1, false));
                    editJsonFile(smartHouse);
                    out.write("Light is OFF");
                    DataBaseService.handleLightSwitch("0");

                    out.flush();
                    break;
                } else if (stringData.equalsIgnoreCase("lighton")) {
                    smartHouse.setLight(new Light(1, true));
                    editJsonFile(smartHouse);
                    out.write("Light is ON");
                    DataBaseService.handleLightSwitch("1");

                    out.flush();
                    break;
                } else if (stringData.equalsIgnoreCase("dooropen")) {
                    smartHouse.setDoor(new Door(1, true));
                    editJsonFile(smartHouse);
                    out.write("Door is OPEN");
                    DataBaseService.handleDoorSwitch("OPEN");
                    out.flush();
                    break;
                } else if (stringData.equalsIgnoreCase("doorclosed")) {
                    smartHouse.setDoor(new Door(1, false));
                    editJsonFile(smartHouse);
                    DataBaseService.handleDoorSwitch("Closed");
                    out.write("Door is CLOSED");
                    out.flush();
                    break;
                } else if (stringData.equalsIgnoreCase("windowopen")) {
                    smartHouse.setWindow(new Window());
                    editJsonFile(smartHouse);
                    out.write("Window is OPEN");
                    out.flush();
                    break;
                } else if (stringData.equalsIgnoreCase("windowclosed")) {
                    smartHouse.setWindow(new Window());
                    editJsonFile(smartHouse);
                    out.write("Window is CLOSED");
                    out.flush();
                    break;
                } else if (stringData.equalsIgnoreCase("Bye")) {
                    client.close();
                    break;
                } else if (stringData.equalsIgnoreCase("Connected")) {
                    String tempC = in.readLine();
                    String tempF = in.readLine();
                    System.out.println("Temp now in C : " + tempC);
                    System.out.println("Temp now in F: " + tempF);
                    Temp temp = new Temp(Float.parseFloat(tempC), Float.parseFloat(tempF));


                    DataBaseService.handleTemperatureSensor(Integer.parseInt(tempC));
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
                    in.close();
                    break;
                } else {
                    char[] chars = {'m', 'n', 'b'};
                    out.write(chars);
                    out.close();
                    client.close();
//                            System.out.println(inputLine);
//                            out.write(inputLine);
//                            out.flush();
                }
                end = true;
            }
            //System.out.println("password"  +  pin);

            // if the value sent from android is equal to the pin read from database then send "Correct"



        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }


    // this method should check whether the string being sent follows a certain structure
public static void dataBaseReference(String stringData) {



        if (stringData.equals("LIGHT") || stringData.equals("DARK")) {



        }else  if (stringData.equals("OPEN") || stringData.equals("CLOSED")) {
            DataBaseService.handleDoorSwitch(stringData);

        }else {



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


