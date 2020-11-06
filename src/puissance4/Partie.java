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
    //Création de la classe partie et de ses attributs
    Joueur []ListeJoueurs = new Joueur [2];
    Grille GrilleJeu = new Grille();
    Joueur joueurCourant; 
    
    //Création de variables couleurs, utilisées dans les méthodes suivantes
    String couleur1;
    String couleur2;
    
    //Attribution aléatoire d'une couleur aux joueurs
    public void attribuerCouleursAuxJoueurs() {
        /* On génère un nb aléatoire correspondant à une des 2 couleurs (rouge/jaune) ce qui va nous permettre
        d'attribuer une couleur aléatoirement à chaque joueur
        */
        Random rand = new Random();
        int nbAleatoire = rand.nextInt(2);
        //En fonction du résultat, on attribue une couleur à chaque variable :
        if (nbAleatoire==0) {
            couleur1 = "Rouge";
            couleur2="Jaune";
        }
        else {
            couleur1 = "Jaune";
            couleur2="Rouge";
        }
        //On attribue la couleur correspondante à chaque joueur
        ListeJoueurs[0].affecterCouleur(couleur1);
        ListeJoueurs[0].couleur=couleur1;
        ListeJoueurs[1].affecterCouleur(couleur2);
        ListeJoueurs[1].couleur=couleur2;
        //On précise la couleur de chaque joueur
        System.out.println(ListeJoueurs[0].nom + " a les jetons " + couleur1+"s");
        System.out.println(ListeJoueurs[1].nom + " a les jetons " + couleur2+"s");
    }
    
    //Initialisation de la partie
    public void initialiserPartie() {
        // On commence par initialiser la grille
        GrilleJeu.viderGrille(); 
        
        //On initialise maintenant la partie :
        
        //Création des joueurs
        //Demande le nom des joueurs et attribue le nom correspondant à chaque joueur de la liste
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
        for (int i=0; i<21; i++) { //Crée 21 jeton de la couleur correspondant à chaque joueur
            joueur1.ajouterJeton(new Jeton(joueur1.couleur));
            joueur2.ajouterJeton(new Jeton(joueur2.couleur)); 
        }
        
        
        //Choix aléatoire de l'ordre de jeu
        Random rand0 = new Random();
        int nbAleatoire0 = rand0.nextInt(2);
        if (nbAleatoire0==0) { //On génère un nombre aléatoire qu'on relie à un ordre de jeu
            //En fonction du nombre, l'ordre de jeu change
            joueurCourant=ListeJoueurs[0];
        }
        else {
            joueurCourant=ListeJoueurs[1];
        }
        //On précise qui commence
        System.out.println("C'est à "+ joueurCourant.nom + " de jouer");
        
        /* 2 désintégrateurs sont placés au même endroit que des trous noirs. On crée
        donc une boucle se répétant 2 fois qui permet de générer 2 nombres aléatoires correspondant
        à la ligne et colonne de la grille sur lesquels on place un désintégrateur + un trou noir */
        for (int i = 0; i<2; i++) {
            int nbAleatoire1, nbAleatoire2;
            Random rand1 = new Random();
            nbAleatoire1 = rand1.nextInt(6);
            nbAleatoire2 = rand1.nextInt(7);
            while (GrilleJeu.Cellules[nbAleatoire1][nbAleatoire2].presenceTrouNoir()==true) {
                //S'il y a deja un trou noir dans la case, on en génère une nouvelle
                nbAleatoire1 = rand1.nextInt(6);
                nbAleatoire2 = rand1.nextInt(7);
            } 
            if (GrilleJeu.Cellules[nbAleatoire1][nbAleatoire2].presenceTrouNoir()==false) {
                //Sinon, on place le trou noir et le désintégrateur
                GrilleJeu.placerTrouNoir(nbAleatoire1,nbAleatoire2);
                GrilleJeu.placerDesintegrateur(nbAleatoire1,nbAleatoire2); 
            } 
            
        }
        //On répète le même procédé pour les 3 trous noirs restants en vérifiant bien qu'il n'y ait pas déjà de trou noir dans la case
        for (int i = 0; i<3; i++) {
            int nbAleatoire1, nbAleatoire2;
            Random rand2 = new Random();
            nbAleatoire1 = rand2.nextInt(6);
            nbAleatoire2 = rand2.nextInt(7);
            while (GrilleJeu.Cellules[nbAleatoire1][nbAleatoire2].presenceTrouNoir()==true) {
                nbAleatoire1 = rand2.nextInt(6);
                nbAleatoire2 = rand2.nextInt(7);
            } 
            if (GrilleJeu.Cellules[nbAleatoire1][nbAleatoire2].presenceTrouNoir()==false) {
                GrilleJeu.placerTrouNoir(nbAleatoire1,nbAleatoire2);
            }
        }
        
        
        //puis pour les désintégrateurs sans oublier la vérification sur la présence de trou noir
        for (int i = 0; i<3; i++) {
            int nbAleatoire1, nbAleatoire2;
            Random rand3 = new Random();
            nbAleatoire1 = rand3.nextInt(6);
            nbAleatoire2 = rand3.nextInt(7);            
            while (GrilleJeu.Cellules[nbAleatoire1][nbAleatoire2].presenceTrouNoir()==true) {
                nbAleatoire1 = rand3.nextInt(6);
                nbAleatoire2 = rand3.nextInt(7);
            }   
            if (GrilleJeu.Cellules[nbAleatoire1][nbAleatoire2].presenceTrouNoir()==false) {
               GrilleJeu.placerDesintegrateur(nbAleatoire1,nbAleatoire2);
           } 
        }
        
        //On affiche la grille initiale contenant les 5 trous noirs/désintégrateurs
        GrilleJeu.afficherGrilleSurConsole();
        
    }
    
        
    //Création du menu d'actions du joueur
    public int menu() {
        //Création d'une variable représentant le choix du joueur
        Scanner sc;       
        int rep;
        sc = new Scanner(System.in);
        System.out.println("1 : Poser un jeton");
        System.out.println("2 : Utiliser un désintégrateur");
        System.out.println("3 : Récupérer un jeton");
        rep=sc.nextInt();
        //On vérifie que le choix est possible
        while (rep>3 || rep<1) { //On vérifie que le choix est valide
            System.out.println("Erreur, choix non valide");
            rep=sc.nextInt();
        }
        return rep; //Retourne la réponse
    }
    
    //Création des différentes actions du menu :
    
    
    public void poserJeton() {
        //Demande la colonne dans laquelle le joueur veut poser son jeton
        Scanner sc;
        sc = new Scanner(System.in);
        if (joueurCourant.nombreJetons>0) { //On vérifie que le joueur a encore des jetons
            System.out.println("Choisir une colonne : ");
            int j = sc.nextInt()-1; //avec j l'indice de la colonne
            //On vérifie que le choix est valide
            while (j>7 || j<0) {
                System.out.println("Erreur, choix non valide");
                j = sc.nextInt()-1;
            }
            /* Tant que la colonne est pleine, on demande au joueur d'en choisir une autre
            Dès que la colonne n'est pas remplie, le jeton sera ajouté automatiquement grâce
            à la méthode ajouterJetonDansColonne*/
            while (GrilleJeu.ajouterJetonDansColonne(joueurCourant, j)==false) {
                System.out.println("La colonne est déjà pleine, veuillez en choisir une autre : ");
                j = sc.nextInt()-1;
            }        
            GrilleJeu.afficherGrilleSurConsole(); //On affiche la grille après ajout
        }
        else {
            System.out.println("Vous n'avez plus de jetons");
        }
    }
    
    public boolean activerDesintegrateur() {
        //On vérifie si le joueur a bien un désintégrateur
        boolean res;
        if (joueurCourant.nombreDesintegrateurs==0) {
            System.out.println("Erreur, vous n'avez pas de désintégrateur");
            res = false;
        }
        else {
            //On demande quel jeton doit être désintégré et on vérifie que la case existe
            Scanner sc;
            sc = new Scanner(System.in);      
            System.out.println("Veuillez indiquer le jeton à désintégrer : ");
            System.out.println("Numéro de ligne : ");
            int i = sc.nextInt()-1; //avec i l'indice de la ligne
            while (i>5 || i<0) {
                System.out.println("Erreur, choix non valide");
                System.out.println("Numéro de ligne : ");
                i = sc.nextInt()-1;
            }
            
            System.out.println("Numéro de colonne : ");
            int j = sc.nextInt()-1; //et j l'indice de la colonne
            while (j>6 || j<0) {
                System.out.println("Erreur, choix non valide");
                System.out.println("Numéro de colonne : ");
                j = sc.nextInt()-1;
            }
            //On vérifie que la case n'est pas vide et on supprime le jeton
            while (GrilleJeu.supprimerJeton(i, j)==false) {
                //Si la case est vide on en demande une autre
                System.out.println("Erreur, case vide. Choisir une autre case : ");
                System.out.println("Numéro de ligne : ");
                i = sc.nextInt()-1;
                while (i>5 || i<0) {
                    System.out.println("Erreur, choix non valide");
                    System.out.println("Numéro de ligne : ");
                    i = sc.nextInt()-1;
                }
                System.out.println("Numéro de colonne : ");
                j = sc.nextInt()-1;
                while (j>6 || j<0) {
                    System.out.println("Erreur, choix non valide");
                    System.out.println("Numéro de colonne : ");
                    j = sc.nextInt()-1;
                }
            }
            //On tasse la grille après avoir supprimé le jeton
            GrilleJeu.tasserGrille(j);
            
            //On enlève un désintégrateur au joueur
            joueurCourant.utiliserDesintegrateur();
            res=true;
            
        }
        GrilleJeu.afficherGrilleSurConsole(); //On affiche la grille mise à jour
        return res;
    }
    
    public boolean reprendreJeton(){
        boolean res;
        //On demande quel jeton le joueur veut récupérer et on vérifie que la case existe
        Scanner sc;
        sc = new Scanner(System.in);      
        System.out.println("Veuillez indiquer le jeton à récupérer : ");
        System.out.println("Numéro de ligne : ");
        int i = sc.nextInt()-1; //avec i l'indice de la ligne
        while (i>5 || i<0) {
            System.out.println("Erreur, choix non valide");
            System.out.println("Numéro de ligne : ");
            i = sc.nextInt()-1;
        }
        System.out.println("Numéro de colonne : ");
        int j = sc.nextInt()-1; //et j l'indice de la colonne
        while (j>6 || j<0) {
            System.out.println("Erreur, choix non valide");
            System.out.println("Numéro de colonne : ");
            j = sc.nextInt()-1;
        }
        //On vérifie que le jeton appartient bien au joueur et que la case n'est pas vide
        if ((GrilleJeu.Cellules[i][j].lireCouleurDuJeton().equals(joueurCourant.couleur)) && GrilleJeu.celluleOccupee(i,j)==true) {
            joueurCourant.ajouterJeton(GrilleJeu.recupererJeton(i,j));
            //On tasse la grille
            GrilleJeu.tasserGrille(j);       
            res=true;
        }  
        else {
            System.out.println("Erreur, cette case est vide ou ce jeton ne vous appartient pas");
            res=false;
        }
        GrilleJeu.afficherGrilleSurConsole(); //On affiche la nouvelle grille
        return res;
    }
    
    //Changement de joueur
    public Joueur changementJoueur(Joueur unJoueur) { //Permet de changer le joueur courant après chaque tour
        if (ListeJoueurs[0]==joueurCourant) {
            return ListeJoueurs[1];
        }
        else {
            return ListeJoueurs[0];
        }
    }
                
    public void choix() {
        
        System.out.println("Que voulez-vous faire ?");
        int rep;
        
        /*Appel à la fonction menu puis à la méthode souhaitée en fonction du choix de
        l'utilisateur*/
        
        do { 
            rep = menu(); 
            switch (rep) {
                case 1: 
                    poserJeton();                    
                    break;
                case 2:
                    boolean res; //On regarde si l'action s'est bien déroulée
                    res = activerDesintegrateur();
                    if (res==false){ //Si non, le joueur peut jouer à nouveau
                        joueurCourant=changementJoueur(joueurCourant);
                    }
                    break;
                case 3: 
                    boolean res1; //Même chose qu'au dessus
                    res1 = reprendreJeton();
                    if (res1==false){
                        joueurCourant=changementJoueur(joueurCourant);
                    }
                    break;                
            }
            
            joueurCourant=changementJoueur(joueurCourant); //Change de joueur à la fin du tour de jeu
            System.out.println("C'est au tour de " + joueurCourant.nom +" de jouer"); //Précise qui joue
        } while (rep<=3 && GrilleJeu.etreGagnantePourJoueur(ListeJoueurs[0])==false && GrilleJeu.etreGagnantePourJoueur(ListeJoueurs[1])==false);
        // BD : changement du test pour inclure les 2 gagnants  
        //On sort de la boucle quand le choix est supérieur à 3 ou quand un gagnant est repéré
        
    }
    
    //Lance la partie
    public void debuterPartie() {
        initialiserPartie();
        choix();
    }    
    
}
