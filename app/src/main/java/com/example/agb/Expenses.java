package com.example.agb;

public class Expenses {

    public String clothing, food, studies, home, transport, technology, health, groceries, entertainment, other;

    public Expenses() {
    }

    public Expenses(String clothing, String food, String studies, String home, String transport, String technology, String health, String groceries, String entertainment, String other ){
        this.food = food;
        this.clothing = clothing;
        this.health = health;
        this.entertainment = entertainment;
        this.studies = studies;
        this.home = home;
        this.transport = transport;
        this.technology = technology;
        this.groceries = groceries;
        this.other = other;
    }

    public String getClothing() {
        return clothing;
    }

    public void setClothing(String clothing) {
        this.clothing = clothing;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getStudies() {
        return studies;
    }

    public void setStudies(String studies) {
        this.studies = studies;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getGroceries() {
        return groceries;
    }

    public void setGroceries(String groceries) {
        this.groceries = groceries;
    }

    public String getEntertainment() {
        return entertainment;
    }

    public void setEntertainment(String entertainment) {
        this.entertainment = entertainment;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
