package net.a6te.lazycoder.savejourney.modelClass;

import java.util.ArrayList;


public class Emergency {
    private String location;
    private String category;
    private String name;
    private String phoneNo;

    public Emergency(String location, String category, String name, String phoneNo) {
        this.location = location;
        this.category = category;
        this.name = name;
        this.phoneNo = phoneNo;
    }
    public Emergency(){

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public ArrayList<Emergency> getAllData(){//this for only temporary use

        ArrayList<Emergency> getAllEmergencyData = new ArrayList<>();

        getAllEmergencyData.add(new Emergency("Mirpur","Police","DIG","01773465276000"));
        getAllEmergencyData.add(new Emergency("Mirpur","Police","SP","01973465276000"));
        getAllEmergencyData.add(new Emergency("Mirpur","Police","SP shahin","01573465276000"));
        getAllEmergencyData.add(new Emergency("Mirpur","Police","OC Rofiq","01997465276000"));
        getAllEmergencyData.add(new Emergency("Mirpur","Hospital","Ambulance All time","01793465276000"));
        getAllEmergencyData.add(new Emergency("Mirpur","Hospital","Ambulance Fahad","01773465276000"));
        getAllEmergencyData.add(new Emergency("Mirpur","Hospital","Ambulance dhaka Hospital","01973465276000"));
        getAllEmergencyData.add(new Emergency("Mirpur","Hospital","Ambulance rakib","01993465276000"));

        return getAllEmergencyData;
    }
}
