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
    //Création de la classe cellule et de ses attributs :
    Jeton jetonCourant;
    boolean trouNoir;
    boolean desintegrateur;
    
    public Cellule() { 
        //Création du constructeur permettant d'initialiser les valeurs de la classe
        jetonCourant=null;
        trouNoir = false;
        desintegrateur = false;
    }
    
    public boolean affecterJeton(Jeton unJeton) {
        //Permet d'ajouter un jeton à une cellule vide
        if (jetonCourant==null) { //On vérifie qu'il n'y a pas déjà de jeton
            jetonCourant=unJeton; //Le jeton passé en paramètre est affecté à la cellule
            return true; //On retourne vrai pour signifier que tout c'est bien passé
        }
        else {
            return false; //On retourne faux si l'ajout n'a pas eu lieu (cas d'une cellule déjà pleine)
        }
    }
    
    //Permet de récupérer le jeton d'une cellule
    public Jeton recupererJeton() {
        Jeton jetonRecup = jetonCourant;//On crée une variable de type Jeton dans laquelle on stocke le jeton de la cellule
        jetonCourant=null;//Le jeton de la cellule devient null == pas de jeton
        return jetonRecup; //On retourne le jeton anciennement dans la cellule
    }
    
    //Permet de supprimer un jeton
    public boolean supprimerJeton() {
        if (jetonCourant==null ) { //On vérifie que la cellule n'est pas vide
            return false; //Retourne faux si c'est le cas
        }
        else {
            jetonCourant=null; //Sinon on vide la cellule
            return true; //Retourne vrai car la suppression a bien eu lieue
        }
    }
    
    //Permet de placer un trou noir
    public boolean placerTrouNoir() {
        if (trouNoir == true) { //On vérifie qu'il n'y en a pas déjà un
            return false; //Retourne faux si c'est le cas
        }
        else {
            trouNoir=true; //Sinon on modifie la valeur de trouNoir
            return true; //Retourne vrai
        }   
    }
    
    //Permet de placer un désintégrateur, même procédé que pour le trou noir
    public boolean placerDesintegrateur() {
        if (desintegrateur == true) {
            return false;
        }
        else {
            desintegrateur=true;
            return true;
        }   
    }
    
    //Renvoi vrai s'il y a un trou noir, et faux sinon
    public boolean presenceTrouNoir() {
        return trouNoir;
    }
    
    //Meme chose mais pour un desintegrateur
    public boolean presenceDesintegrateur() {
        return desintegrateur;      
    }
    
    //Donne la couleur du jeton de la cellule
    public String lireCouleurDuJeton() {
        if (jetonCourant==null) { //Si la cellule est vide, retourne vide
            return "VIDE";
        }
        else { //Sinon, retourne la couleur du jeton
            return jetonCourant.lireCouleur();
        }        
    }
    
    //Permet de récupérer desintegrateur
    public boolean recupererDesintegrateur() {
        if (desintegrateur==true) { //Verifie que la cellule contient bien un desintegrateur
            desintegrateur=false; //Change sa valeur
            return true; //Retourne vrai si tout c'est bien passé
        }
        else { //Retourne faux si la case ne contient pas de desintegrateur
            return false;
        }
    }
    
    //Active le trou noir et notifie le joueur
    public boolean activerTrouNoir() {
        if(trouNoir==true) { //Vérifie qu'il y a un trou noir
            jetonCourant=null; //Supprime le jeton 
            trouNoir=false; //Fait disparaitre le trou noir après utilisation
            System.out.println("Un trou noir a engloutit votre jeton"); //Prévient le joueur
            return true; //Retourne vrai après utilisation
        }
        else { //Pas de trou noir==retourne faux
            return false;
        }
    }
     
}
