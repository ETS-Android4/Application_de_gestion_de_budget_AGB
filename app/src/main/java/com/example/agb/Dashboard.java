package com.example.agb;

public class Dashboard {

    public String firstName, lastName, phoneNumber, monthlyIncome, savings;

    public Dashboard(){}

    // Class Dashboard: faite et utilisée pour l'interface Dashboard "HomeActivity" : prend comme parametres les donnees qui seront entrées à l'interface Dashboard
    // cet objet créé sera utilisé pour la mise en firebase de ces données

    public Dashboard(String firstName, String lastName, String phoneNumber, String monthlyIncome, String savings){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.monthlyIncome = monthlyIncome;
        this.savings = savings;
    }


}
