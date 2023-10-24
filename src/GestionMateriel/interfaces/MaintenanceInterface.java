/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionMateriel.interfaces;

import GestionMateriel.entities.Maintenance;
import java.util.List;

/**
 *
 * @author HP
 */
public interface MaintenanceInterface {
    
    public void ajouterMaintenance(Maintenance v);
    public Maintenance rechercherMaintenance(int id);
    public void modifierMaintenance(Maintenance v, int id);
    public void supprimerMaintenance(int id);
    public List<Maintenance> afficherMaintenance();
    
}
