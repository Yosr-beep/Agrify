/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.pi;

import GestionMateriel.entities.Materiel;
import GestionMateriel.entities.TypeMateriel;
import static GestionMateriel.entities.TypeMateriel.silo;
import GestionMateriel.utils.MyConnection;
import GestionMateriel.services.MaterielCRUD;


/**
 *
 * @author HP
 */
public class ProjetPi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         
        MyConnection cn = MyConnection.getInstance();
        
        //Materiel v = new Materiel(tracteur, "en panne", 50,200,25000);
        //Materiel v1 = new Materiel('silo', "en panne", 12,200,30000);
        MaterielCRUD cv= new MaterielCRUD();
       // cv.ajouterMateriel(v1);
       //cv.modifierMateriel(v1, 3);
       //cv.supprimerMateriel(3);
       System.out.println( cv.afficherMateriel());
        
       
    }
    
}
