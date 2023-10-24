/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionMateriel.services;

import GestionMateriel.entities.Materiel;
import GestionMateriel.interfaces.MaterielInterface; 
import GestionMateriel.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Statement;
import java.util.ArrayList;



/**
 *
 * @author HP
 */
public class MaterielCRUD implements MaterielInterface {

    Connection conn = MyConnection.getInstance().getconn();
    Statement ste;
        
    
    
    
    @Override
    public void ajouterMateriel(Materiel v) {
        String req = "INSERT INTO `Materiel`(`id`, `type`, `etat`, `capacite_masse`, `capacite_volume`, `prix`) VALUES ('" + v.getId() + "','" + v.getType() + "','" + v.getEtat() + "','" + v.getCapacite_masse() + "','" + v.getCapacite_volume() + "','" + v.getPrix() + "')";
        try {
            ste = conn.createStatement(); 
            ste.executeUpdate(req); 
            System.out.println("véhicule ajoutée!"); 
        } catch (SQLException ex) {
            System.out.println( ex.getMessage());
        }
        
    }


    @Override
    public Materiel recherchermateriel(int id) {
    
        try {
        String query = "SELECT * FROM materiel WHERE id = " + id;
        Statement stm = conn.createStatement();
        ResultSet result = stm.executeQuery(query);

        if (result.next()) {
            Materiel m = new Materiel();
            m.setId(result.getInt("id"));
            m.setType(result.getString("type"));
            m.setEtat(result.getString("etat"));
            m.setCapacite_masse(result.getInt("capacite_masse"));
            m.setCapacite_volume(result.getInt("capacite_volume"));
            m.setPrix(result.getInt("prix"));
            
            return m;
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }  
        return null;
    }

   @Override
    public void modifierMateriel(Materiel v, int id) {
    String req = "UPDATE `Materiel` SET `type`=?, `etat`=?, `capacite_masse`=?, `capacite_volume`=?, `prix`=? WHERE `id`=?";
    try {
        PreparedStatement st = conn.prepareStatement(req);
        st.setString(1, v.getType());
        st.setString(2, v.getEtat());
        st.setInt(3, v.getCapacite_masse());
        st.setInt(4, v.getCapacite_volume());
        st.setInt(5, v.getPrix());
        st.setInt(6, id);
        st.executeUpdate(); 
        System.out.println("véhicule modifié!");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

    @Override
    public void supprimerMateriel(int id) {
        String req = "DELETE FROM `Materiel` WHERE id="+id+"";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
               System.out.println("véhicule supp!");
        } catch (SQLException ex) {
            System.out.println( ex.getMessage());
        }
    }

    @Override
    public List<Materiel> afficherMateriel() {
        List <Materiel> ve =new ArrayList<>();
        String requet="SELECT `id`, `type`, `etat`, `capacite_masse`, `capacite_volume`, `prix` FROM `Materiel` WHERE 1";
        Statement stm ;
        try {
            stm = conn.createStatement();
            ResultSet res =stm.executeQuery(requet);
            while (res.next()){
               Materiel rp = new Materiel();
               rp.setId(res.getInt(1));
               rp.setType(res.getString(2));
               rp.setEtat(res.getString(3));
               rp.setCapacite_masse(res.getInt(4));
               rp.setCapacite_volume(res.getInt(5));
               rp.setPrix(res.getInt(6));
               ve.add(rp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ve;
    }  
       
    private List<Materiel> materielList; // Assuming this is your list of Materiel objects

    // Constructor and other methods...

    // Check if a similar Materiel item with different ID exists
    public boolean isSimilarItemExists(Materiel newItem) {
        for (Materiel existingItem : materielList) {
            // Compare type, etat, capacite_m, capacite_v, and prix
            if (existingItem.getType().equals(newItem.getType())
                && existingItem.getEtat().equals(newItem.getEtat())
                && existingItem.getCapacite_masse() == newItem.getCapacite_masse()
                && existingItem.getCapacite_volume() == newItem.getCapacite_volume()
                && existingItem.getPrix() == newItem.getPrix()) {
                return true; // Similar item found
            }
        }
        return false; // No similar item found
    }
}



   