import javax.swing.*;
import java.sql.SQLOutput;

public class Labyrinthe {
    private boolean[][] murs;
    private Personnage personnage;
    private Sortie sortie;

    /**
     * methode permettant de recuperer le contenu d'une case
     *
     * @param x : valeur de la ligne sur laquelle est situee la case
     * @param y : valeur de la colonne sur laquelle est situee la case
     * @return retour : character correspondant a l initial du contenu de la case
     */
    public char getChar(int x, int y) {
        char retour = '.';
        if (!murs[x][y]) {
            retour = 'X';
        } else if (personnage.getX() == x && personnage.getY() == y) {
            retour = 'P';
        } else if (sortie.getX() == x && sortie.getY() == y) {
            retour = 'S';
        }
        return retour;
    }

    /**
     * methode de classe permettant de recuperer la case suivante en fonction de la direction
     *
     * @param x         : numero de la ligne actuelle
     * @param y         : numero de la colonne actuelle
     * @param direction : direction
     * @return suiv : case suivante
     */
    public static int[] getSuivant(int x, int y, String direction) {
        int[] suiv = {x, y};
        try {
            switch (direction) {
                case "HAUT":
                    suiv[0] = x - 1;
                case "Bas":
                    suiv[0] = x + 1;
                case "GAUCHE":
                    suiv[1] = y - 1;
                case "DROITE":
                    suiv[1] = y + 1;
                default:
                    throw new ActionInconnueException(direction);
            }


        } catch (ActionInconnueException e) {
            System.out.println("Action : "+ direction + " n'est pas valide");

        }
        return suiv;
    }

    /**
     * methode qui permet de deplacer le personnage en modifiant ses coordonees
     * @param action : chaine de caracteres
     */
    public void deplacerPerso(String action) {
        //on regarde la case suivante

        // si c'est un mur on s'arrete
        // quand le personnage s arrete on regarde si il est sur la sortie en appellant la methode etre fini


    }

}
