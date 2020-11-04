/*
 **DANAYS Clara
* LUNARDI Fanny
* 23/10/2020
 */
package puissance4;

/**
 *
 * @author flunardi
 */
public class Joueur {
    String nom;
    String couleur;
    Jeton ListeJetons []= new Jeton [21];
    int nombreDesintegrateurs;
    int nombreJetons;
    
    public Joueur(String unNom) {
        
        nom=unNom;
    }
    
    public void affecterCouleur (String uneCouleur) {
        
        couleur = uneCouleur;
    }
    
    public void ajouterJeton (Jeton unJeton) {
        
        for (int i=0; i<21; i++) {
            ListeJetons[i]=unJeton;
        }
    }
    
    public void obtenirDesintegrateurs() {
        
        nombreDesintegrateurs = nombreDesintegrateurs++;
    }
    
    public boolean utiliserDesintegrateur() {
        
        if (nombreDesintegrateurs==0) {
            return false;
        }
        else {
            nombreDesintegrateurs = nombreDesintegrateurs--;
            return true;
        }
        
        
    }
    
}
