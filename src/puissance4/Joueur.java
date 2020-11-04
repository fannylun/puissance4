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
        nombreDesintegrateurs=0;
        nombreJetons=0;
    }
    
    public void affecterCouleur (String uneCouleur) {
        
        couleur = uneCouleur;
    }
    
    public void ajouterJeton (Jeton unJeton) {
        
        for (int i=0; i<21; i++) {
            ListeJetons[i]=unJeton;
            nombreJetons++;
        }
    }
    
    public Jeton enleverJeton () {
        nombreJetons=nombreJetons-1;
        return ListeJetons[nombreJetons];
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
