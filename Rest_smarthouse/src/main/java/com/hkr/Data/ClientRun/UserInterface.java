package com.hkr.Data.ClientRun;

import com.hkr.Data.Model.Device;

import java.util.List;
import java.util.Scanner;

public class UserInterface {

    DeviceManager postman = new DeviceManager();

    public void menu() {

        int choice;
        boolean flag=true;

        do{
            System.out.println("\n" +"1. Get all devices" + "\n"
                    + "2. Get one specific device"+ "\n"
                    + "3. Update a device"+ "\n");

            Scanner in = new Scanner(System.in);
            System.out.println("Your choice: ");

            choice = Integer.parseInt(in.nextLine());

            switch(choice){

                case 1:
                    List<Device> devices = postman.getAllDevices();
                    System.out.println("Devices:" + devices.size());
                    System.out.println("******************************");
                    for(Device t : devices){
                        System.out.println("Type: " + t.getLightSwitch());
                        System.out.println("Value: " + t.getLightSwitch());

                        System.out.println("******************************");

                    }
                    break;
                case 2:
                    System.out.println("Type your device type: ");
                    String type = in.nextLine();
                    Device t = postman.getDevice(type);
                  //  System.out.println("Type: " + t.getDeviceType());
                 //   System.out.println("Value: " + t.getDeviceValue());

                    break;
                case 3:
                    System.out.println("Type the device type (Light or Door): ");
                    String ssn = in.nextLine();

                    System.out.println("Type new value (LightOn/LightOff or doorOpen/doorClosed ) : ");
                    String address = in.nextLine();

                    postman.updateCustomer(ssn,address);
                    System.out.println("Updated!");
                    System.out.println("Type device type to see the new version: ");
                    String deviceType = in.nextLine();
                    Device device2 = postman.getDevice(deviceType);
                   // System.out.println("type : " + device2.getDeviceType());
                   // System.out.println("value: " + device2.getDeviceValue());


                    break;
                case 4:
                    //Complete
                    break;
                case 5:
                    String msg = postman.exit();
                    System.out.println(msg);
                    flag=false;
                    break;

                default:
                    //Complete
                    break;
            }
        }while(flag);


    }
}
