package com.example.agb;


import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    // Classe utilisée pour l'Ajout des transactions dens la liste des transactions de chaque catégorie

    private String somme, categorie;

    Transaction(){

    }

    Transaction(String somme, String categorie){
        this.somme = somme;
        this.categorie = categorie;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String AfficheTransaction(){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        String S =  this.categorie + " : " + this.somme.toString() + " " + dtf.format(now).toString();
        // format d'affichage des transactions ajoutées : " Categorie SommeD'Argent dd MM yyyy HH:mm:ss "
        return S;
    }

}