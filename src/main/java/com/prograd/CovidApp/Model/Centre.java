package com.prograd.CovidApp.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection="VaccinationCenters")
public class Centre {
    @Id
    private int id;
    private String name;
    private String location;
    private double price;
    private String dosage;
    private int totalAvailability;
    private int currentAvailability;
    private ArrayList<User> candidates;

    public Centre(int id, String name, String location, double price, String dosage, int totalAvailability, ArrayList<User> candidates) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.price = price;
        this.dosage = dosage;
        this.totalAvailability = totalAvailability;
        this.currentAvailability = totalAvailability;
        this.candidates = candidates;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCurrentAvailability() {
        return currentAvailability;
    }

    public void setCurrentAvailability(int currentAvailability) {
        this.currentAvailability = currentAvailability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public int getTotalAvailability() {
        return totalAvailability;
    }

    public void setTotalAvailability(int totalAvailability) {
        // needs edit
        this.totalAvailability = totalAvailability;
    }

    public ArrayList<User> getCandidates() {
        return candidates;
    }

    public void setCandidates(ArrayList<User> candidates) {
        this.candidates = candidates;
    }

    @Override
    public String toString() {
        return "Centre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", price=" + price +
                ", dosage='" + dosage + '\'' +
                ", availability=" + totalAvailability +
                '}';
    }
}
