/*
 *DANAYS Clara
* LUNARDI Fanny
* 23/10/2020
 */
package puissance4;

/**
 *
 * @author flunardi
 */
public class Cellule {
    Jeton jetonCourant;
    boolean trouNoir;
    boolean desintegrateur;
    
    public Cellule() {
        jetonCourant=null;
        trouNoir = false;
        desintegrateur = false;
    }
    
    public boolean affecterJeton(Jeton unJeton) {
        if (jetonCourant==null) {
            jetonCourant=unJeton;
            return true;
        }
        else {
            return false;
        }
    }
    
    //permet de récupérer le jeton d'une cellule
    public Jeton recupererJeton() {
        Jeton jetonRecup = jetonCourant;
        jetonCourant=null;
        return jetonRecup;
    }
    
    public boolean supprimerJeton() {
        if (jetonCourant==null ) {
            return false;
        }
        else {
            jetonCourant=null;
            return true;
        }
    }
    
    public boolean placerTrouNoir() {
        if (trouNoir == true) {
            return false;
        }
        else {
            trouNoir=true;
            return true;
        }   
    }
    
    public boolean placerDesintegrateur() {
        if (desintegrateur == true) {
            return false;
        }
        else {
            desintegrateur=true;
            return true;
        }   
    }
    
    public boolean presenceTrouNoir() {
        return trouNoir;
    }
    
    public boolean presenceDesintegrateur() {
        return desintegrateur;      
    }
    
    public String lireCouleurDuJeton() {
        if (jetonCourant==null) {
            return "VIDE";
        }
        else {
            return jetonCourant.lireCouleur();
        }        
    }
    
    public boolean recupererDesintegrateur() {
        if (desintegrateur==true) {
            desintegrateur=false;
            return true;
        }
        else {
            return false;
        }
    }
    
    public boolean activerTrouNoir() {
        if(trouNoir==true) {
            jetonCourant=null;
            trouNoir=false;
            System.out.println("Un trou noir a engloutit votre jeton");
            return true;
        }
        else {
            return false;
        }
    }
     
}
