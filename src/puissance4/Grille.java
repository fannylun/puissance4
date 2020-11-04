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
    
    public boolean colonneRemplie(int i) {
        if (Cellules[5][i]!=null){
            return true;
        }
        else {
            return false;
       }      
    }
    
    public boolean ajouterJetonDansColonne(Jeton unJeton, int i) { /*avec i, l'indice de la colonne*/
        if (colonneRemplie(i)==true) {
            return false;
        }
        else {
            
        }
        for (int j=6; j<0; j--) {
            if  (Cellules[j][i] == null) {
                Cellules[j][i].jetonCourant = unJeton;
            }
        }
        if (Cellules[0][i]!=null){
            return false;
        }
        else {
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
                    Cellules2[i][j]= "-";
                }
                else if (Cellules[i][j].presenceTrouNoir()== true) {
                    Cellules2[i][j]= "X";
                }
                else if (Cellules[i][j].presenceDesintegrateur()== true) {
                    Cellules2[i][j]= "O";
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
        if (Cellules[i][j])
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
