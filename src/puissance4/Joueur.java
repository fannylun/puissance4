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
        
        ListeJetons[nombreJetons++]=unJeton;
    }
    
    
    public Jeton enleverJeton () {
        nombreJetons=nombreJetons-1;
        return ListeJetons[nombreJetons];
    }
    
    public void obtenirDesintegrateurs() {
        
        nombreDesintegrateurs++;
        System.out.println("Vous avez gagné un désintégrateur");
    }
    
    public boolean utiliserDesintegrateur() {
        boolean res;
        if (nombreDesintegrateurs==0) {
            res= false;
        }
        else {
            nombreDesintegrateurs = nombreDesintegrateurs--;
            res= true;
        }        
        System.out.print("Vous avez " + nombreDesintegrateurs + " désintégrateurs");
        return res;
    }
    
}
