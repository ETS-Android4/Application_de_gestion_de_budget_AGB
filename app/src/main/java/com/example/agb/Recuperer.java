package com.example.agb;

public class Recuperer {

    // Classe utilisée dans le procesus d'ajout des transactions

    private int somme = 0;

    public Recuperer() {
    }

    public Recuperer(int somme){
        this.somme = somme ;
    }

    public int Afficher(){
        int n = this.somme;
        return(n);
    }


}
