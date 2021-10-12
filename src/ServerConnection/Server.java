package ServerConnection;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {



    public static ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();

    // we can add as many concurrent client as we want, we have 10 clients for now
    public static ExecutorService pool = Executors.newFixedThreadPool(10);


    // start the Server Class first before the Client class
    public static void main(String[] args) throws IOException {
        // write your code here

        ServerSocket ss = new ServerSocket(2400);

        try {

            while (true) {
                System.out.println("waiting for client connection");
                Socket socket = ss.accept();
                System.out.println("client connected");


                // this class will handle the mutlithreaded server
                ClientHandler clientThread = new ClientHandler(socket);
                pool.execute(clientThread);

            }
        } catch (Exception ex) {
            System.out.println("Too many threads");
            ex.printStackTrace();
        } finally {
            ss.close();
        }
    }
    }

