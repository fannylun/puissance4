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
    Cellule [][]Cellules = new Cellule [6][7];
    
    public boolean colonneRemplie(int i) { //avec i l'indice de la colonne
        if (Cellules[5][i]!=null){
            return true;
        }
        else {
            return false;
       }      
    }
    
    public boolean ajouterJetonDansColonne(Joueur unJoueur, int i) { //avec i, l'indice de la colonne
        int j=0; //avec j l'indice de la ligne
        if (colonneRemplie(i)==true) {
            return false;
        }
        else {
            //On cherche la première cellule vide en partant du bas
            while(Cellules[j][i]!=null) {
                j++;
            }
            //On ajoute ensuite un jeton dans la case concernée et on enlève un jeton dans la liste du joueur           
            Jeton unJeton = unJoueur.enleverJeton();
            Cellules[j][i].jetonCourant=unJeton;
        
            //On vérifie s'il y a un trou noir
            if (Cellules[j][i].presenceTrouNoir()==true) {
                Cellules[j][i].activerTrouNoir();
            }
            
            //On vérifie s'il y a un désintégrateur
            if (Cellules[j][i].presenceDesintegrateur()==true) {
                Cellules[j][i].recupererDesintegrateur();
                unJoueur.obtenirDesintegrateurs();
                      
            }
            return true;
        }            
        
    }
    
    public boolean etreRemplie() {
        boolean res = false;
        for (int i=0; i<7; i++) {
            if (Cellules[0][i]==null){
                res = false;
            }
            else {
                res=true;
            }
        }
        return res;
    }
    
    public void viderGrille() {
        for (int i=0; i<7; i++) {
            for (int j=0; j<6;j++) {
                Cellules[j][i]=null;
            }
        }
    }
            
    public void afficherGrilleSurConsole() {
        String [][]Cellules2 = new String[6][7];
        for (int i=0; i<7; i++) {
            for (int j=0; j<8; j++) {
                if (Cellules[i][j]==null) {
                    Cellules2[i][j]= "X"; //On affiche un X si la case est vide
                }
                else if (Cellules[i][j].presenceTrouNoir()== true) {
                    Cellules2[i][j]= "T";
                }
                else if (Cellules[i][j].presenceDesintegrateur()== true) {
                    Cellules2[i][j]= "D";
                }
                else {
                    if (Cellules[i][j].lireCouleurDuJeton() == "Rouge") {
                        Cellules2[i][j]= "R";
                    }
                    if (Cellules[i][j].lireCouleurDuJeton() == "Jaune") {
                        Cellules2[i][j]= "J";
                    }
                    
                }
                System.out.print(Cellules2[i][j]);
               
            }
            System.out.print("\n");
        }
    }
    
    public boolean celluleOccupee(int i, int j) {
        if (Cellules[i][j]!=null) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public String lireCouleurDuJeton(int i, int j) {
        return Cellules[i][j].jetonCourant.lireCouleur();
    }
    
    public boolean etreGagnantePourJoueur (Joueur unJoueur) {
        boolean res=false;
        String CouleurDuJoueur=unJoueur.couleur;
        
        //Si 4 jetons sont alignés en ligne
        for(int i=0; i<6; i++){
            int nb_alignés=0;
            for(int j=0; j<7; j++){
                if (Cellules[i][j].lireCouleurDuJeton().equals(CouleurDuJoueur)){
                    nb_alignés=nb_alignés+1;
                }
                else{
                    nb_alignés=0;
                }
                if (nb_alignés==4){
                    System.out.print(unJoueur + "a gagné la partie");
                    res=true;                    
                }
            }
        }
        
        //Si 4 jetons sont alignés en colonne
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
                    System.out.print(unJoueur + "a gagné la partie");
                    res=true;               
                }
            }
        }
        
        //Si 4 jetons sont alignés en diagonales
        for(int i=0;i<6;i++){
            int nb_alignés=0;
            for(int j=0;j<4;j++){
                if (Cellules[i][j].lireCouleurDuJeton().equals(CouleurDuJoueur)){
                    if (Cellules[i+1][j+1].lireCouleurDuJeton().equals(CouleurDuJoueur)){
                        nb_alignés=nb_alignés+1;
                    }
                    else{
                        nb_alignés=0;
                    }
                    if (nb_alignés==4){
                        System.out.print(unJoueur + "a gagné la partie");
                        res=true;                     
                    }
                    
                }
            } 
            for(int j=3; j<7;j++){
                if (Cellules[i][j].lireCouleurDuJeton().equals(CouleurDuJoueur)){
                    if (Cellules[i+1][j-1].lireCouleurDuJeton().equals(CouleurDuJoueur)){
                        nb_alignés=nb_alignés+1;
                    }
                    else{
                        nb_alignés=0;
                    }
                    if (nb_alignés==4){
                        System.out.print(unJoueur + "a gagné la partie");
                        res=true;                      
                    }
                    
                }
            }
        }
        return res;
        
    }
    
    
    public void tasserGrille (int i) { /*avec i, la colonne*/
        for (int j=6; j<0; j--) {
            if (Cellules[j][i]==null) {
                Cellules[j][i]=Cellules[j+1][i];
                Cellules[j+1][i]=null;
            }
        }
    }
      
    public boolean placerTrouNoir (int i , int j){
        if (Cellules[i][j].presenceTrouNoir()==true){
            return false;
        }
        else{
            Cellules[i][j].placerTrouNoir();
            return true;
        }
    }
    
    public boolean placerDesintegrateur (int i , int j){
        if (Cellules[i][j].presenceDesintegrateur()==true){
            return false;
        }
        else{
            Cellules[i][j].placerDesintegrateur();
            return true;
        }
    }
    
    public boolean supprimerJeton (int i, int j){
        boolean res;
        res=Cellules[i][j].supprimerJeton();
        return res;
    }
    
    public Jeton recupererJeton (int i , int j){
        Jeton res;
        res= Cellules[i][j].recupererJeton();
        Cellules[i][j].supprimerJeton();
        return res;
    }

}
