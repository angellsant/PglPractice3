package com.example.luis.applicationthree;

public class CarConstructor {

    String nameConstructor;
    String yearBirdth;
    String founderName;

    public CarConstructor(String nameConstructor, String yearBirdth, String founderName) {
        this.nameConstructor = nameConstructor;
        this.yearBirdth = yearBirdth;
        this.founderName = founderName;
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

    public String getFounderName() {
        return founderName;
    }

    public void setFounderName(String founderName) {
        this.founderName = founderName;
    }
}
