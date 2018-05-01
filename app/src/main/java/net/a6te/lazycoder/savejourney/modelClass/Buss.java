package net.a6te.lazycoder.savejourney.modelClass;

import java.util.ArrayList;

/**
 * Created by ashraf on 19/5/17.
 */

public class Buss {
    private String bussName;
    private String averageStar;

    public Buss(String bussName, String averageStar) {
        this.bussName = bussName;
        this.averageStar = averageStar;
    }

    public Buss() {
    }

    public String getBussName() {
        return bussName;
    }

    public void setBussName(String bussName) {
        this.bussName = bussName;
    }

    public String getAverageStar() {
        return averageStar;
    }

    public void setAverageStar(String averageStar) {
        this.averageStar = averageStar;
    }

    public ArrayList<Buss> getAllBussList(){
        ArrayList<Buss> bussList = new ArrayList<>();

        bussList.add(new Buss("Desh Travels","5.0"));
        bussList.add(new Buss("Sakura Paribahan","5.0"));
        bussList.add(new Buss("SaintMartin Paribahan","4.5"));
        bussList.add(new Buss("Aqib Enterprise","4.9"));
        bussList.add(new Buss("Seven Star Paribahan","4.7"));
        bussList.add(new Buss("Dream Line Special","4.0"));
        bussList.add(new Buss("Falguni Modhumoti (Pvt) Ltd","4,2"));
        bussList.add(new Buss("Bablu Enterprise ","5.0"));
        bussList.add(new Buss("Kingfisher Travels","5.0"));
        bussList.add(new Buss("Sakal Sandhya Enterprise","5.0"));
        bussList.add(new Buss("Dhaka Express","3.9"));
        bussList.add(new Buss("Sarker Travels","3.5"));
        bussList.add(new Buss("Shanti Paribahan ","3.6"));
        bussList.add(new Buss("Baghdad Express","3.4"));
        bussList.add(new Buss("Saintmartin Service","3.9"));
        bussList.add(new Buss("Econo service","4.0"));
        bussList.add(new Buss("Sonartori Paribahan","4.5"));
        bussList.add(new Buss("Shyamoli Paribahan","4.6"));
        bussList.add(new Buss("SA Travels (Pvt.) Ltd","4.5"));
        bussList.add(new Buss("Green Saintmartin Express","4.5"));
        bussList.add(new Buss("H.R Travels","4.7"));
        bussList.add(new Buss("Shah Ali Paribahan","4.3"));
        bussList.add(new Buss("S.Alam Service","4.5"));
        bussList.add(new Buss("Relax Paribahan","4.7"));
        bussList.add(new Buss("AK Travels","4.9"));
        return bussList;
    }
}

