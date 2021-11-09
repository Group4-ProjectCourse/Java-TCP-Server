package com.example.androidtcpconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.BatchUpdateException;

public class LoginActivity extends AppCompatActivity {
    private EditText pinCode;

    public static final int SERVERPORT = 2400;
    public static final String SERVER_IP = "194.47.46.148";

    private Button login;
private Button connectToServer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pinCode = (EditText) findViewById(R.id.pinCode);
        login = (Button) findViewById(R.id.login);
        connectToServer = (Button) findViewById(R.id.connectToServer);

connectToServer.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        sendMessage("Client Is connected");

    }
});
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String pin_code = pinCode.getText().toString();


                try {
                    loginUser(pin_code);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void loginUser(String pin_code) throws IOException {

        sendMessage(pin_code);


    }

    // this method will send a message to the server when a change occurs
    private void sendMessage(String toString) {

        final Handler handler = new Handler();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final Socket s = new Socket(SERVER_IP, SERVERPORT);


                    // message to server
                    PrintWriter output = new PrintWriter(s.getOutputStream(), true);
                    output.println(toString);


                    // Message we may recieve from server
                    BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    final String st = input.readLine();
                    //System.out.println("From server" + st);
recieveMessageFromServer(st);

                    output.close();

                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        });
        thread.start();
    }

    public String recieveMessageFromServer(String pattern) throws IOException {


        if (pattern.equals("Correct")) {
            System.out.println("Login Correct");
            //Toast.makeText(LoginActivity.this, "Login Successful!",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        }else {

            System.out.println("Login Incorrect try again ");
            //Toast.makeText(LoginActivity.this, "Incorrect Pin Code!",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this,LoginActivity.class));
            finish();
        }

    return pattern;}

}
