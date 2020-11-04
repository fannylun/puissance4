/*
 *DANAYS Clara
* LUNARDI Fanny
* 23/10/2020
 */
package puissance4;

import java.util.Random;

/**
 *
 * @author flunardi
 */
public class Partie {
    Joueur []ListeJoueurs = new Joueur [2];
    Grille GrilleJeu;
    Joueur joueurCourant; 
    
    /* On génère un nb aléatoire correspondant à une des 2 couleurs ce qui permet
    d'attribuer une couleur aléatoirement à chaque joueur
    */
    String couleur1;
    String couleur2;
    Random rand = new Random();
    int nbAleatoire = rand.nextInt(2);
    
    public void attribuerCouleursAuxJoueurs() {
       if (nbAleatoire==0) {
           couleur1 = "Rouge";
           couleur2="Jaune";
        }
       else {
           couleur1 = "Jaune";
           couleur2="Rouge";
        }
       
       ListeJoueurs[0].affecterCouleur(couleur1);
       ListeJoueurs[1].affecterCouleur(couleur2);
       
    }
    
    public void initialiserPartie() {
        GrilleJeu.viderGrille(); /* On commence par vider la grille*/
        /* 2 désintégrateurs sont placés au même endroit que des trous noirs. On crée
        donc une boucle se répétant 2 fois qui permet de générer 2 nombres aléatoires correspondant
        à la ligne et colonne de la grille sur lesquels on place un désintégrateur + un trou noir
        */
        for (int i = 0; i<2; i++) {
            int nbAleatoire1, nbAleatoire2;
            Random rand = new Random();
            nbAleatoire1 = rand.nextInt(6);
            nbAleatoire2 = rand.nextInt(7);
            GrilleJeu.placerTrouNoir(nbAleatoire1,nbAleatoire2); 
            GrilleJeu.placerDesintegrateur(nbAleatoire1,nbAleatoire2); 
        }
        /*On répète le même procédé pour les 3 trous noirs restants puis pour les désintégrateurs*/
        for (int i = 0; i<3; i++) {
            int nbAleatoire1, nbAleatoire2;
            Random rand = new Random();
            nbAleatoire1 = rand.nextInt(6);
            nbAleatoire2 = rand.nextInt(7);
            GrilleJeu.placerTrouNoir(nbAleatoire1,nbAleatoire2);
        }
        
        for (int i = 0; i<3; i++) {
            int nbAleatoire1, nbAleatoire2;
            Random rand = new Random();
            nbAleatoire1 = rand.nextInt(6);
            nbAleatoire2 = rand.nextInt(7);
            GrilleJeu.placerDesintegrateur(nbAleatoire1,nbAleatoire2); 
        }
        
        Jeton jeton1;
        jeton1.Jeton(couleur1);
        
        ListeJoueurs[0].ajouterJeton(jeton1);        
    }
    
    public void debuterPartie() {
        attribuerCouleursAuxJoueurs();
        initialiserPartie();
    }
    
    
}
