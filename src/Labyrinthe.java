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
        try {
            int[] suiv = {x, y};
            switch (direction) {
                case "HAUT":
                    suiv[0] = x - 1;
                case "Bas":
                    suiv[0] = x + 1;
                case "GAUCHE":
                    suiv[1] = y - 1;
                case "DROITE":
                    suiv[1] = y + 1;
            }

            return suiv;
        } catch (ActionInconnueException e) {
            e.printStackTrace();
        }
    }
}

}
