/*
 *DANAYS Clara
* LUNARDI Fanny
* 23/10/2020
 */
package puissance4;

import java.util.Random;
import java.util.Scanner;

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
       ListeJoueurs[0].couleur=couleur1;
       ListeJoueurs[1].affecterCouleur(couleur2);
       ListeJoueurs[1].couleur=couleur2;
       
       System.out.println(ListeJoueurs[0].nom + "est de couleur" + couleur1);
       System.out.println(ListeJoueurs[1].nom + "est de couleur" + couleur2);
    }
    
    public void initialiserPartie() {
        // On commence par initialiser la grille
        GrilleJeu.viderGrille(); 
        
        //On initialise maintenant la partie :
        
        //Création des joueurs         
        System.out.println("Entrez les noms des joueurs : ");
        Scanner sc = new Scanner(System.in);
        System.out.println("Joueur 1 : ");
        Joueur joueur1 = new Joueur(sc.nextLine());
        System.out.println("Joueur 2 : ");
        Joueur joueur2 = new Joueur(sc.nextLine());
        ListeJoueurs[0]=joueur1;
        ListeJoueurs[1]=joueur2;
        
        //Attribution d'une couleur
        attribuerCouleursAuxJoueurs();
        
        //Création des jetons
        joueur1.ajouterJeton(new Jeton(joueur1.couleur));
        joueur2.ajouterJeton(new Jeton(joueur2.couleur));
        
        //Choix aléatoire de l'ordre de jeu
        Random rand0 = new Random();
        int nbAleatoire0 = rand0.nextInt(2);
        if (nbAleatoire0==0) {
            joueurCourant=ListeJoueurs[0];
        }
        else {
            joueurCourant=ListeJoueurs[1];
        }
        
        /* 2 désintégrateurs sont placés au même endroit que des trous noirs. On crée
        donc une boucle se répétant 2 fois qui permet de générer 2 nombres aléatoires correspondant
        à la ligne et colonne de la grille sur lesquels on place un désintégrateur + un trou noir */
        for (int i = 0; i<2; i++) {
            int nbAleatoire1, nbAleatoire2;
            Random rand1 = new Random();
            nbAleatoire1 = rand1.nextInt(6);
            nbAleatoire2 = rand1.nextInt(7);
            GrilleJeu.placerTrouNoir(nbAleatoire1,nbAleatoire2); 
            GrilleJeu.placerDesintegrateur(nbAleatoire1,nbAleatoire2); 
        }
        //On répète le même procédé pour les 3 trous noirs restants 
        for (int i = 0; i<3; i++) {
            int nbAleatoire1, nbAleatoire2;
            Random rand2 = new Random();
            nbAleatoire1 = rand2.nextInt(6);
            nbAleatoire2 = rand2.nextInt(7);
            GrilleJeu.placerTrouNoir(nbAleatoire1,nbAleatoire2);
        }
        
        //puis pour les désintégrateurs
        for (int i = 0; i<3; i++) {
            int nbAleatoire1, nbAleatoire2;
            Random rand3 = new Random();
            nbAleatoire1 = rand3.nextInt(6);
            nbAleatoire2 = rand3.nextInt(7);
            GrilleJeu.placerDesintegrateur(nbAleatoire1,nbAleatoire2); 
        }
        
        //On affiche la grille initiale
        GrilleJeu.afficherGrilleSurConsole();
        
    }
    
    public void debuterPartie() {
        initialiserPartie();
        //Tant qu'il n'y a aucun gagnant, boucle de jeu puis affiche grille à l'écran
    }
    
    
}
