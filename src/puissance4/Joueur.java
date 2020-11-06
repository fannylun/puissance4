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
    
    //Création de la classe joueur et de ses attributs
    String nom;
    String couleur;
    Jeton ListeJetons []= new Jeton [21];
    int nombreDesintegrateurs;
    int nombreJetons;
    
    public Joueur(String unNom) {
        //Création du constructeur + initialisation des attributs
        nom=unNom;
        nombreDesintegrateurs=0;
        nombreJetons=0;
    }
    
    //Permet d'affecter la couleur en paramètre au joueur
    public void affecterCouleur (String uneCouleur) {        
        couleur = uneCouleur;
    }
    
    //Permet de remplir la liste de jetons du joueur en fonction de son nb de jetons 
    public void ajouterJeton (Jeton unJeton) {        
        ListeJetons[nombreJetons++]=unJeton;
    }    
    
    //Retire un jeton dans la liste du joueur 
    public Jeton enleverJeton () {
        nombreJetons=nombreJetons-1; //nb de jetons diminué
        return ListeJetons[nombreJetons]; //Renvoie la nouvelle liste
    }
    
    //Incrémente le nb de désintégrateur et notifie le joueur
    public void obtenirDesintegrateurs() {        
        nombreDesintegrateurs++;
        System.out.println("Vous avez gagné un désintégrateur");
    }
    
    //Utilisation d'un désintégrateur
    public boolean utiliserDesintegrateur() {
        boolean res; //Création variable résultat
        if (nombreDesintegrateurs==0) { //On vérifie que le joueur a bien un ou + desintegrateur(s)
            res= false; //Si non, retourne faux
        }
        else { //Si oui :
            nombreDesintegrateurs--; //On retire un desintegrateur au joueur
            res= true; //Retourne vraie
        }        
        System.out.print("Vous avez " + nombreDesintegrateurs + " désintégrateurs"); //Rappelle le nb de desintegrateurs du joueur
        return res; //Renvoie le résultat
    }
}
