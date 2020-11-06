/*
 * DANAYS Clara
 * LUNARDI Fanny
 * 23/10/2020
 */
package puissance4;

/**
 *
 * @author flunardi
 */
public class Grille {
    //Creation de la classe grille et de son attribut
    Cellule [][]Cellules = new Cellule [6][7];
    
    Grille() {
        //Constructeur, permet de remplir la grille de cellules
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                Cellules[i][j] = new Cellule();
            }
        }
    }
    
    //Permet de savoir si une colonne est remplie ou non
    public boolean colonneRemplie(int i) { //avec i l'indice de la colonne en paramètre
        if (Cellules[5][i].jetonCourant!=null){ //On se place à la dernière ligne et la i-ème colonne et on regarde s'il y a un jeton ou non
            return true; //Retourne vraie si dernière case de la colonne est remplie
        }
        else {
            return false; //Faux dans le cas contraire
       }      
    }
    
    //Permet d'ajouter un jeton dans la colonne choisie
    public boolean ajouterJetonDansColonne(Joueur unJoueur, int i) { //avec i, l'indice de la colonne en paramètre
        int j=0; //avec j l'indice de la ligne
        if (colonneRemplie(i)==true) { //Si la colonne est remplie, retourne faux car on ne peux plus ajouter de jeton
            return false;
        }
        else { //Sinon
            //On cherche la première cellule vide de la colonne en partant du bas
            while(Cellules[j][i].jetonCourant!=null) { //Tant que la cellule n'est pas vide on passe à la ligne suivante
                j++;
            }
            //On ajoute ensuite un jeton dans la case concernée et on enlève un jeton dans la liste du joueur           
            Jeton unJeton = unJoueur.enleverJeton();
            Cellules[j][i].jetonCourant=unJeton;
        
            //On vérifie s'il y a un trou noir
            if (Cellules[j][i].presenceTrouNoir()==true) {
                Cellules[j][i].activerTrouNoir(); //Si oui, on l'active (voir classe cellule)
            }
            
            //On vérifie s'il y a un désintégrateur
            if (Cellules[j][i].presenceDesintegrateur()==true) {
                Cellules[j][i].recupererDesintegrateur(); //Si oui, on le retire de la cellule
                unJoueur.obtenirDesintegrateurs(); //et on l'ajoute au joueur
                      
            }
            return true; //Renvoie vrai après ajout du jeton
        }
    }
    
    //Vérifie si la grille est remplie
    public boolean etreRemplie() {
        boolean res = false; //Création d'une variable résultat
        for (int i=0; i<7; i++) { //avec i, l'indice de la colonne
            //Parcoure la dernière ligne pour savoir si elle est vide
            if (Cellules[5][i].jetonCourant==null){
                res = false; //Si oui, retourne faux
            }
            else { //Si elle est pleine, retourne vrai
                res=true;
            }
        }
        return res; //Retourne le résultat
    }
    
    //Permet de vider la grille
    public void viderGrille() {
        //parcoure la grille cellule par cellule et la vide
        for (int i=0; i<7; i++) { //avec j l'indice de la colonne
            for (int j=0; j<6;j++) { //et i celui de la ligne
                Cellules[j][i].jetonCourant=null;
            }
        }
    }
    
    //Transforme la grille de cellules en une grille de string à afficher sur la console
    public void afficherGrilleSurConsole() {
        String [][]Cellules2 = new String[6][7]; //Création de la grille de string qu'on affichera
        //parcoure la grille cellule par cellule
        for (int i=0; i<6; i++) { //avec i l'indice de la ligne
            for (int j=0; j<7; j++) { //et j celui de la colonne
                if (Cellules[i][j].jetonCourant==null) { //Si il n'y a pas de jeton
                    if (Cellules[i][j].presenceTrouNoir()== true) { //mais qu'il y a un trou noir
                        Cellules2[i][j]= "T "; //la cellule prend la valeur T
                    }
                    else if (Cellules[i][j].presenceDesintegrateur()== true) { //s'il y a un desintegrateur
                    Cellules2[i][j]= "D "; //la cellule prend la valeur D
                    }
                    else { 
                        Cellules2[i][j]= "X "; //On affiche un X si la case est vide
                    }                    
                }
                else { //Si la cellule contient un jeton, la cellule de la nouvelle grille prend la valeur de l'initiale de la couleur du jeton
                    if (Cellules[i][j].lireCouleurDuJeton().equals("Rouge")) {
                        Cellules2[i][j]= "R ";
                    }
                    if (Cellules[i][j].lireCouleurDuJeton().equals("Jaune")) {
                        Cellules2[i][j]= "J ";
                    } 
                }                   
            }            
        }
        //On affiche ensuite la grille ligne par ligne en inversant les lignes pour que la grille soit affiché à l'endroit
        //(permet de bien avoir le jetonen sur la dernière ligne de la console lorsqu'on choisit une colonne)
        for (int i=0; i<6; i++) {
            for (int j=0; j<7; j++) {
                System.out.print(Cellules2[5-i][j]); //Affiche cellule par cellule
            }
            System.out.print("\n"); //Change de ligne à chaque fin de ligne
        }
        
    }
    
    //Renvoie vrai s'il y a un jeton R/J dans la cellule, faux sinon
    public boolean celluleOccupee(int i, int j) {
        if (Cellules[i][j].jetonCourant.couleur.equals("Rouge") || Cellules[i][j].jetonCourant.couleur.equals("Jaune")) {
            return true;
        }
        else {
            return false;
        }
    }
    
    // Renvoie la couleur du jeton de la cellule en paramètre 
    public String lireCouleurDuJeton(int i, int j) { //Avec i la ligne et j la colonne
        if (Cellules[i][j].jetonCourant==null) { //S'il n'y a pas de jeton, retourne vide
            return "VIDE";                    
        }
        else {
            return Cellules[i][j].jetonCourant.lireCouleur(); //Sinon retourne la couleur 
        }
        
    }
    
    //Permet de définir les cas gagnants
    public boolean etreGagnantePourJoueur (Joueur unJoueur) {
        boolean res=false; //Création d'une variable résultat
        String CouleurDuJoueur=unJoueur.couleur; //Création d'une variable contenant la couleur du joueur en paramètre
        
        //Si 4 jetons sont alignés en ligne :
        for(int i=0; i<6; i++){ //Parcoure les lignes
            int nb_alignés=0; //Création d'un compteur de jetons alignés initialisé à 0
            for(int j=0; j<7; j++){ //parcoure les cellules des lignes
                if (Cellules[i][j].lireCouleurDuJeton().equals(CouleurDuJoueur)){ //Cherche un jeton de la couleur du joueur
                    nb_alignés=nb_alignés+1; //Incrémente le compteur à chaque fois qu'il en trouve un
                }
                else{
                    nb_alignés=0; //Sinon retour à 0
                }
                if (nb_alignés==4){ //Dès que le compteur atteint 4
                    System.out.print(unJoueur.nom + " a gagné la partie"); //Prévient qu'il y a un gagnant
                    res=true; //Renvoie vrai s'il y a un gagnant                    
                }
            }
        }
        
        //Si 4 jetons sont alignés en colonne, même chose mais on parcoure les cellules des colonnes
        for(int j=0; j<7; j++){
            int nb_alignés=0;
            for(int i=0; i<6; i++){
                if (Cellules[i][j].lireCouleurDuJeton().equals(CouleurDuJoueur)){
                    nb_alignés=nb_alignés+1;
                }
                else{
                    nb_alignés=0;
                }
                if (nb_alignés==4){
                    System.out.print(unJoueur.nom + " a gagné la partie");
                    res=true;               
                }
            }
        }
        
        //Si 4 jetons sont alignés en diagonale montante
        for(int i=0;i<3;i++){ //la base de la diagonale ne peut pas se trouver en dehors du rectangle 3x4 formé par les 3 premières lignes et 4 premières colonnes
            int nb_alignés=0; //On initialise le compteur
            for(int j=0;j<4;j++){ //On parcoure case par case le rectangle en question
                if (Cellules[i][j].lireCouleurDuJeton().equals(CouleurDuJoueur)){ //Si on trouve un jeton appartenant au joueur
                    nb_alignés=1; //Compteur incrémenté
                    while (Cellules[i+1][j+1].lireCouleurDuJeton().equals(CouleurDuJoueur)&& nb_alignés!=4){
                        //Tant que les jetons de la diagonale sont de la même couleur, et que le compteur n'atteint pas 4
                        nb_alignés=nb_alignés+1; //Compteur incrémenté
                        if (nb_alignés==4){ //Si on atteint 4, on a un gagnant
                            System.out.print(unJoueur.nom + " a gagné la partie");
                            res=true; //Retourne vrai                     
                        }
                        else { //Sinon, on incrémente i et j
                            i++;
                            j++;
                        }  
                    }     
                }
            }
        }
        //Si 4 jetons sont alignés en diagonale descendante
        for (int i=3;i<6;i++){ //le rectangle formé par le bases possible de diagonales se trouve dans le coin supérieur gauche            
            //Même raisonnement que pour l'autre diagonale
            int nb_alignés=0;           
            for(int j=0;j<4;j++){
                if (Cellules[i][j].lireCouleurDuJeton().equals(CouleurDuJoueur)){
                    nb_alignés=1;
                    while (Cellules[i-1][j+1].lireCouleurDuJeton().equals(CouleurDuJoueur)&& nb_alignés!=4){
                        //Sauf que cette fois le numéro de ligne diminue puisque la diagonale descend
                        nb_alignés=nb_alignés+1;
                        
                        if (nb_alignés!=4) { //On incrémente j et on diminue i tant que le compteur n'atteint pas 4
                            i--;
                            j++;
                        }
                    }
                    if (nb_alignés==4){ //Quand on atteint 4, retourne vrai
                            System.out.print(unJoueur.nom + " a gagné la partie");
                            res=true;
                    }
                    else { //Sinon, réinitialise le compteur
                        nb_alignés=0;
                    }
                }
            }
        }
        return res; //Retourne le résultat
    } 
    
    //Permet de tasser la grille après activation trou noir/desintegrateur
    public void tasserGrille (int i) { //avec i, la colonne
        for (int j=0; j<6; j++) { //Parcoure les lignes de la colonne en paramètre
            if (Cellules[j][i]==null) { //cherche une cellule vide
                Cellules[j][i]=Cellules[j+1][i]; //La transforme en la cellule de la ligne du dessus
                Cellules[j+1][i]=null; //Vide la cellule du dessus
            }
        }
    }      
    
    //Permet de palcer un trou noir dans la grille
    public boolean placerTrouNoir (int i , int j){ //avec i la ligne et j la colonne
        if (Cellules[i][j].presenceTrouNoir()==true){ //Si la cellule contient un trou noir
            return false; //retourne faux car on ne peux pas en mettre un 2eme
        }
        else{ //Sinon place un trou noir et retourne vrai
            Cellules[i][j].placerTrouNoir();
            return true;
        }
    }
    
    //Meme raisonnement pour un desintegrateur
    public boolean placerDesintegrateur (int i , int j){
        if (Cellules[i][j].presenceDesintegrateur()==true){
            return false;
        }
        else{
            Cellules[i][j].placerDesintegrateur();
            return true;
        }
    }
    
    //Supprime le jeton de la cellule en paramètre
    public boolean supprimerJeton (int i, int j){ //avec i la ligne et j la colonne
        boolean res;
        res=Cellules[i][j].supprimerJeton();
        return res; //renvoie vrai/faux si la suppression a eu lieue/ou non
    }
    
    //Recupère le jeton de la cellule en parametre
    public Jeton recupererJeton (int i , int j){ //avec i la ligne et j la colonne
        Jeton res;
        res= Cellules[i][j].recupererJeton(); //reprend le jeton
        Cellules[i][j].supprimerJeton();//le supprime dans la cellule
        return res;//renvoie le résultat
    }

}
