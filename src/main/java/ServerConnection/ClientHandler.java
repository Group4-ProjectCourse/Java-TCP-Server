package ServerConnection;

import Data.DataBaseService;
import Model.CallBack;
import Model.RegisterUser;

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
    String pin =  DataBaseService.handleUserPin("Pin");
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





                //System.out.println("password"  +  pin);

                // if the value sent from android is equal to the pin read from database then send "Correct"
                    if (pin != null && Objects.equals(pin, stringData)) {
                        out.println("Correct");
                        //out.println(InetAddress.getLocalHost().getHostAddress());

                    }else {
                        //now we are logged in and should be able to change the different states
                        out.println(" "+ stringData.toUpperCase());

                        dataBaseReference(stringData.toUpperCase());








                }


                end = true;
            }


        } catch (NullPointerException e ) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                end = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    // this method should check whether the string being sent follows a certain structure
public static void dataBaseReference(String stringData) {



        if (stringData.equals("0") || stringData.equals("1")) {

            DataBaseService.handleLightSwitch(stringData);


        }else  if (stringData.equals("OPEN") || stringData.equals("CLOSED")) {
            DataBaseService.handleDoorSwitch(stringData);

        }else {



        }

    }

}
