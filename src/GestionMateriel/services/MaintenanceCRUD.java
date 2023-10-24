/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionMateriel.services;

import GestionMateriel.entities.Maintenance;
import GestionMateriel.interfaces.MaintenanceInterface;
import GestionMateriel.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class MaintenanceCRUD implements MaintenanceInterface {
    
    Connection conn = MyConnection.getInstance().getconn();
    Statement ste;
        
    
    
    
    @Override
    public void ajouterMaintenance(Maintenance v) {
        String req = "INSERT INTO `Maintenance`(`id`, `materiel`, `datedebut`, `datefin`, `description`) VALUES ('" + v.getId() + "','" + v.getMateriel() + "','" + v.getDateDebut() + "','" + v.getDateFin() + "','" + v.getDescription() + "')";
        try {
            ste = conn.createStatement(); 
            ste.executeUpdate(req); 
            System.out.println("véhicule ajoutée!"); 
        } catch (SQLException ex) {
            System.out.println( ex.getMessage());
        }
        
    }


    @Override
    public Maintenance rechercherMaintenance(int id) {
    
        try {
        String query = "SELECT * FROM Maintenance WHERE id = " + id;
        Statement stm = conn.createStatement();
        ResultSet result = stm.executeQuery(query);
/*public class Maintenance {
    private int id;
    private String materiel;
    private String dateDebut;
    private String dateFin;
    private String description;*/

        if (result.next()) {
            Maintenance m = new Maintenance();
            m.setId(result.getInt("id"));
            m.setMateriel(result.getString("materiel"));
            m.setDateDebut(result.getString("datedebut"));
            m.setDateFin(result.getString("datefin"));
            m.setDescription(result.getString("description"));
            
            return m;
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }  
        return null;
    }

   @Override
    public void modifierMaintenance(Maintenance v, int id) {
    String req = "UPDATE `Maintenance` SET `materiel`=?, `datedebut`=?, `datefin`=?, `description`=? WHERE `id`=?";
    try {
        PreparedStatement st = conn.prepareStatement(req);
        st.setString(1, v.getMateriel());
        st.setString(2, v.getDateDebut());
        st.setString(3, v.getDateFin());
        st.setString(4, v.getDescription());
        st.setInt(5, id);
        st.executeUpdate(); 
        System.out.println("Maintenance modifié!");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

    @Override
    public void supprimerMaintenance(int id) {
        String req = "DELETE FROM `Maintenance` WHERE id="+id+"";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
               System.out.println("Maintenance supprimé!");
        } catch (SQLException ex) {
            System.out.println( ex.getMessage());
        }
    }

    @Override
    public List<Maintenance> afficherMaintenance() {
        List <Maintenance> displayed_list =new ArrayList<>();
        String requet="SELECT `id`, `materiel`, `datedebut`, `datefin`, `description` FROM `Maintenance` WHERE 1";
        Statement stm ;
        try {
            stm = conn.createStatement();
            ResultSet res =stm.executeQuery(requet);
            while (res.next()){
                
               Maintenance m = new Maintenance();
               
               m.setId(res.getInt(1));
               m.setMateriel(res.getString(2));
               m.setDateDebut(res.getString(3));
               m.setDateFin(res.getString(4));
               m.setDescription(res.getString(5));
               
               displayed_list.add(m);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return displayed_list;
    }  
    
}
