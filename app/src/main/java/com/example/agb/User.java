package com.example.agb;

public class User {

    public String fullName, age, email;

    public User(){

    }

    // Classe utilisée pour l'enregistrement des nouveaux utilisateurs dans la base de données

    public User(String fullName, String age, String email){
        this.fullName = fullName;
        this.age = age;
        this.email = email;
    }

}
