/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionMateriel.interfaces;

import GestionMateriel.entities.Materiel;
import java.util.List;

/**
 *
 * @author HP
 */
public interface MaterielInterface {
    
    public void ajouterMateriel(Materiel v);
    public Materiel recherchermateriel(int id);
    public void modifierMateriel(Materiel v, int id);
    public void supprimerMateriel(int id);
    public List<Materiel> afficherMateriel();
    
    
}
