package com.hkr.Data.ClientRun;

import com.hkr.Data.Model.Device;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class DeviceManager {

    Client client;
    String baseAddress;

    public DeviceManager(){

        client = ClientBuilder.newClient();
        baseAddress = "http://localhost:8080/testRest_war_exploded/api/devices";
    }
//methods that communicates with server

public List<Device> getAllDevices(){

      List<Device> devices =  client.target(baseAddress)
                                 .request(MediaType.APPLICATION_JSON)
                                .get(new GenericType<List<Device>>(){});

        return devices;
}


public Device getDevice(String type){

//path shall be ...teams/teamId
    WebTarget target=client.target(baseAddress).path("{status}");

    Device device = target.resolveTemplate("status", type)
            .request(MediaType.APPLICATION_JSON)
            .get(Device.class);
    return device;


  }


    public Response updateCustomer(String ssn, String address){
        WebTarget target =client.target(baseAddress).path("{status}");

        Response customer = target.resolveTemplate("status", ssn)
                .request(MediaType.APPLICATION_JSON).put(Entity.entity(address, MediaType.APPLICATION_JSON));
        return customer;

    }

public String exit(){

        if(client != null)
            client.close();
        return "connection closed!";
}
}
