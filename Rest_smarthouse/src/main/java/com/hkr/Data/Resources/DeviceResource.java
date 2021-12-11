
package com.hkr.Data.Resources;

import com.google.gson.Gson;
import com.hkr.Data.FireBaseService;
import com.hkr.Data.Model.CallBack;
import com.hkr.Data.Model.CallBackName;
import com.hkr.Data.Model.Device;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.FileWriter;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@Path("/devices")
public class DeviceResource {



    Device device = new Device();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    synchronized public String getAllDevices() throws ExecutionException, InterruptedException {

        // this makes sure we wait until the data is read from firebase
        CompletableFuture<Device> deviceTestFut = new CompletableFuture<>();
        //System.out.println(device.toString());

        FireBaseService.getAllDevices(new CallBack() {
            @Override
            public Device onCallBack(Device value) {
                device = new Device(value.getLightSwitch(), value.getDoorSwitch(),value.getHumidity(),value.getTemperature(),value.getWindowSwitch());

                System.out.println("Here is the value" + device.toString());

                deviceTestFut.complete(new Device(value.getLightSwitch(), value.getDoorSwitch(),value.getHumidity(),value.getTemperature(),value.getWindowSwitch()));
                return device;
            }
        });


        //

        // get the firebaseData and add it to our model classes
        Device deviceTest = deviceTestFut.get();

        // turn the data into json
        return editJsonFile(deviceTest);


    }


    //Get one specific device with name

    // PAth is basically http://localhost:8080/Rest_smarthouse_war_exploded/api/devices/device/DoorSwitch    DoorSwitch can be changed to LightSwitch to get light state
    @GET

    @Path("device/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    synchronized public String  getDeviceByName(@PathParam("name") String device) throws ExecutionException, InterruptedException {
        CompletableFuture<Device> deviceTestFut = new CompletableFuture<>();
        FireBaseService.getDeviceByName(new CallBackName() {
            @Override
            public String callbackName(Device deviced) {
                deviceTestFut.complete(new Device(deviced.getLightSwitch(), deviced.getDoorSwitch(),deviced.getHumidity(),deviced.getTemperature(),deviced.getWindowSwitch()));

                System.out.println("Here is the value light" + DeviceResource.this.device.getLightSwitch());


                return deviced.toString();
            }

        });


        deviceTestFut.thenAccept(value -> System.out.println("Should workk work " + value.getDoorSwitch()));

        Device deviceTest1 = deviceTestFut.get();

        //
        if (device.equals("DoorSwitch")) {
            return device + " " + deviceTest1.getDoorSwitch();
        } else if (device.equals("LightSwitch")) {
            return device + " " + deviceTest1.getLightSwitch();
        } else  if (device.equals("Humidity")) {
            return device + " " + deviceTest1.getHumidity();

        }else if (device.equals("Temperature")) {
            return device + " " + deviceTest1.getTemperature();
        } else  if( device.equals("WindowSwitch")) {

            return device  + " " +  deviceTest1.getWindowSwitch();
        }else {

            return null;
        }

    }


    @PUT
    @Path("device/{key}/{updateValue}")

   // this one updates the values in firebase


    // url is  basically this if you want to change the light state http://localhost:8080/Rest_smarthouse_war_exploded/api/devices/device/LightSwitch/LIGHT
    // /devices/(the name of the device)/(what value you want to change it to)
   public String updateDevice(@PathParam("key") String key, @PathParam("updateValue") String updateValue) throws ExecutionException, InterruptedException {
        CompletableFuture<Device> deviceTestFut = new CompletableFuture<>();
        FireBaseService.getDeviceByName(new CallBackName() {
            @Override
            public String callbackName(Device deviced) {
                deviceTestFut.complete(new Device(deviced.getLightSwitch(), deviced.getDoorSwitch(),deviced.getHumidity(),deviced.getTemperature(),deviced.getWindowSwitch()));

                System.out.println("Here is the value light" + device.getLightSwitch());


                return deviced.toString();
            }

        });


        Device deviceUpdate = deviceTestFut.get();
        if (key.equals("DoorSwitch")) {
            FireBaseService.updateDevice(key, String.valueOf(updateValue));

            deviceUpdate.setDoorSwitch(String.valueOf(updateValue));

            return deviceUpdate.getDoorSwitch();
        } else if (key.equals("LightSwitch")) {

            FireBaseService.updateDevice(key, String.valueOf(updateValue));
            deviceUpdate.setDoorSwitch(String.valueOf(updateValue));
            return deviceUpdate.getLightSwitch();

        }else if (key.equals("WindowSwitch")) {
            FireBaseService.updateDevice(key, String.valueOf(updateValue));
            deviceUpdate.setWindowSwitch(String.valueOf(updateValue));
            return deviceUpdate.getWindowSwitch();
        }
        // something went wrong
        return  null;

    }

@DELETE

@Path("device/{key}")

// here is the url to delete a device
    //http://localhost:8080/Rest_smarthouse_war_exploded/api/devices/device/name-of-the-device
    public String deleteDevice(@PathParam("key") String key) throws ExecutionException, InterruptedException {

    CompletableFuture<Device> deviceTestFut = new CompletableFuture<>();
    FireBaseService.getDeviceByName(new CallBackName() {
        @Override
        public String callbackName(Device deviced) {
            deviceTestFut.complete(new Device(deviced.getLightSwitch(), deviced.getDoorSwitch(),deviced.getHumidity(),deviced.getTemperature(),deviced.getWindowSwitch()));

            System.out.println("Here is the value light" + device.getLightSwitch());


            return deviced.toString();
        }

    });


    Device deviceUpdate = deviceTestFut.get();
    if (key.equals("DoorSwitch")) {
        FireBaseService.deleteDevice(key);



        return deviceUpdate.getDoorSwitch();
    } else if (key.equals("LightSwitch")) {

        FireBaseService.deleteDevice(key);

        return deviceUpdate.getLightSwitch();

    }else if (key.equals("WindowSwitch")) {
        FireBaseService.deleteDevice(key);

        return deviceUpdate.getWindowSwitch();
    }
    // something went wrong
    return  null;

}


    @PUT
    @Path("sensors/{key}/{updateValue}")

    // this one updates the values in firebase for int values such as temperature

   // http://localhost:8080/Rest_smarthouse_war_exploded/api/devices/device/sensors/Humidity/34
    // /sensors/(the name of the device)/(what value you want to change it to)
    public int updateDevice(@PathParam("key") String key, @PathParam("updateValue") int updateValue) throws ExecutionException, InterruptedException {
        CompletableFuture<Device> deviceTestFut = new CompletableFuture<>();
        FireBaseService.getDeviceByName(new CallBackName() {
            @Override
            public String callbackName(Device deviced) {
                deviceTestFut.complete(new Device(deviced.getLightSwitch(), deviced.getDoorSwitch(),deviced.getHumidity(),deviced.getTemperature(),deviced.getWindowSwitch()));

                System.out.println("Here is the value light" + device.getLightSwitch());


                return deviced.toString();
            }

        });


        Device deviceUpdate = deviceTestFut.get();
        if (key.equals("Temperature")) {
            FireBaseService.updateDeviceInt(key, updateValue);
            deviceUpdate.setTemperature(updateValue);

            return deviceUpdate.getTemperature();
        }else if (key.equals("Humidity")) {
            FireBaseService.updateDeviceInt(key,updateValue);
            deviceUpdate.setHumidity(updateValue);

            return deviceUpdate.getHumidity();
        }
        // something went wrong
        return -1;

    }

    public String  editJsonFile(Device device) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(device);
        try {
            FileWriter writeTofile = new FileWriter("smartHouse.json");
            writeTofile.write(jsonString);
            writeTofile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(jsonString);

   return jsonString;
    }



}

