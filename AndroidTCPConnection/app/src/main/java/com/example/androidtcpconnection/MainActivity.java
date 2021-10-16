package com.example.androidtcpconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import static com.example.androidtcpconnection.MainActivity.SERVERPORT;
import static com.example.androidtcpconnection.MainActivity.SERVER_IP;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    public static final int SERVERPORT = 2400;
    public static final String SERVER_IP = "192.168.1.8";

    public Switch lampSwitch;
    public TextView lamptxt;
    public ImageView lightON;
    private DataBase db = new DataBase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        lampSwitch = (Switch) findViewById(R.id.lampSwitch);
        lightON = (ImageView) findViewById(R.id.lightON);
        lamptxt = (TextView) findViewById(R.id.lamptxt);


        //   String read = in.readLine();
        //  System.out.println("MSG:" + read);



// this method checks the switch, depending on whether it is switch we will update the value in the database
        lampSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (lampSwitch.isChecked()) {
                    lamptxt.setText("LIGHT");




                    sendMessage(lamptxt.getText().toString());
                    db.UpdateLampElement(lamptxt.getText().toString());

                    lightON.setImageResource(R.drawable.lighton);
                } else {
                    lamptxt.setText("DARK");

                    sendMessage(lamptxt.getText().toString());
                    db.UpdateLampElement(lamptxt.getText().toString());
                    lightON.setImageResource(R.drawable.lightoff);
                }
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.lampSwitch:
                sendMessage(lampSwitch.getText().toString());
        }
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
                    PrintWriter output = new PrintWriter(s.getOutputStream(),true);
                    output.println(toString);


                    // Message we may recieve from server
                    BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    final String st = input.readLine();
                    System.out.println("From server" + st);



                    output.close();

                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        });
        thread.start();
    }
    }



