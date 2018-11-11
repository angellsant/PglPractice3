package com.example.luis.applicationthree;

public class CarConstructor {

    String nameConstructor;
    String yearBirdth;

    public CarConstructor(String nameConstructor, String yearBirdth) {
        this.nameConstructor = nameConstructor;
        this.yearBirdth = yearBirdth;
    }

    public String getNameConstructor() {
        return nameConstructor;
    }

    public void setNameConstructor(String nameConstructor) {
        this.nameConstructor = nameConstructor;
    }

    public String getYearBirdth() {
        return yearBirdth;
    }

    public void setYearBirdth(String yearBirdth) {
        this.yearBirdth = yearBirdth;
    }
}
