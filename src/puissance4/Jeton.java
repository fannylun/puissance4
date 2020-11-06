/*
 * *DANAYS Clara
* LUNARDI Fanny
* 23/10/2020
 */
package puissance4;

/**
 *
 * @author flunardi
 */
public class Jeton {
    
    //Création de la classe jeton et de son attribut
    String couleur;
    
    //Constructeur
    public Jeton (String uneCouleur) {
        couleur=uneCouleur;
    }
    
    //Renvoie la couleur du jeton
    public String lireCouleur () {
        return couleur;
    }

}
