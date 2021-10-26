package ServerConnection;

import Data.DataBaseService;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    // start the Server Class first before the Client class
    public static void main(String[] args) throws IOException {

        // This ip can be changed to the ip of the one hosting.
        Socket socket = new Socket("192.168.104.2", 2400);

        BufferedReader server = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        InputStream is = socket.getInputStream();

        try {



            while (true) {
                System.out.println("Press the keyboard");
                String request = keyboard.readLine();

                // if you select quit you will exist the program
                if (request.contains("quit")) break;

                out.println(request);

                String serverResponse = server.readLine();

                System.out.println("server request " + serverResponse);
                // write the booking information to a file named booking


            }
        } finally {
            server.close();
            keyboard.close();
            is.close();

        }
    }


}