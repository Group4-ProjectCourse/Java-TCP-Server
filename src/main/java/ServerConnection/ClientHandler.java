package ServerConnection;

import Data.DataBaseService;

import javax.xml.stream.FactoryConfigurationError;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

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
                System.out.println("clientResponse " + stringData.toLowerCase());
                //dataBaseService.testWriteToDatabase("LIGHT");
                // here we should get the client response such as value change (LAMP= DARK or LAMP = LIGHT)
           dataBaseReference(stringData.toUpperCase());


                out.println(InetAddress.getLocalHost().getHostAddress());
                end = true;
            }


        } catch (NullPointerException | IOException e) {
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



        if (stringData.equals("LIGHT") || stringData.equals("DARK")) {

            DataBaseService.handleLightSwitch(stringData);


        }else  if (stringData.equals("OPEN") || stringData.equals("CLOSED")) {
            DataBaseService.handleDoorSwitch(stringData);

        }else {

        }

    }

}
