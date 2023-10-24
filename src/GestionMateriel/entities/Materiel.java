/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionMateriel.entities;

/**
 *
 * @author HP
 */
public class Materiel {
    private int id;
    private String type;
    private String etat;
    private int capacite_masse;
    private int capacite_volume;
    private int prix;

    public Materiel(int id, String type, String etat, int capacite_masse, int capacite_volume, int prix) {
        this.id = id;
        this.type = type;
        this.etat = etat;
        this.capacite_masse = capacite_masse;
        this.capacite_volume = capacite_volume;
        this.prix = prix;
    }

    public Materiel(String type, String etat, int capacite_masse, int capacite_volume, int prix) {
        this.type = type;
        this.etat = etat;
        this.capacite_masse = capacite_masse;
        this.capacite_volume = capacite_volume;
        this.prix = prix;
    }

    public Materiel() {
    }

    @Override
    public String toString() {
        return "Materiel{" + "id=" + id + ", type=" + type + ", etat=" + etat + ", capacite_masse=" + capacite_masse + ", capacite_volume=" + capacite_volume + ", prix=" + prix + '}';
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getCapacite_masse() {
        return capacite_masse;
    }

    public void setCapacite_masse(int capacite_masse) {
        this.capacite_masse = capacite_masse;
    }

    public int getCapacite_volume() {
        return capacite_volume;
    }

    public void setCapacite_volume(int capacite_volume) {
        this.capacite_volume = capacite_volume;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

   

  
    
    
}

