package ServerConnection;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientHandler extends  Thread {

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;

    BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter key = new BufferedWriter(new OutputStreamWriter(System.out));


    public ClientHandler(Socket clientHandler) throws IOException {
        this.client = clientHandler;
        this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        this.out = new PrintWriter(client.getOutputStream(), true);


    }



    // This class will handle the client request, such as the database request
    // for now it will just send the host address

    public void run() {

        try {
            out.println(InetAddress.getLocalHost().getHostAddress());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
