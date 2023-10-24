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
public class Maintenance {
    private int id;
    private String materiel;
    private String dateDebut;
    private String dateFin;
    private String description;

    public Maintenance() {
    }

    public Maintenance(int id, String materiel, String dateDebut, String dateFin, String description) {
        this.id = id;
        this.materiel = materiel;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.description = description;
    }

    public Maintenance(String materiel, String dateDebut, String dateFin, String description) {
        this.materiel = materiel;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Maintenance{" + "id=" + id + ", materiel=" + materiel + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", description=" + description + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMateriel() {
        return materiel;
    }

    public void setMateriel(String materiel) {
        this.materiel = materiel;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   
    
    
}

