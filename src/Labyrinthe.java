import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Labyrinthe {
    private boolean[][] murs;
    private Personnage personnage;
    private Sortie sortie;

    //constantes labyrinthe
    static char MUR = 'X';
    static char PJ = 'P';
    static char SORTIE = 'S';
    static char VIDE = '.';
    //constantes deplacements
    static String HAUT = "haut";
    static String BAS = "bas";
    static String GAUCHE = "gauche";
    static String DROITE = "droite";


    public Labyrinthe(boolean[][] m, Personnage p, Sortie s) {
        this.murs = m;
        this.personnage = p;
        this.sortie = s;
    }

    /**
     * methode permettant de recuperer le contenu d'une case
     *
     * @param x : valeur de la ligne sur laquelle est situee la case
     * @param y : valeur de la colonne sur laquelle est situee la case
     * @return retour : character correspondant a l initial du contenu de la case
     */
    public char getChar(int x, int y) {
        char retour = murs[x][y] ? MUR : VIDE;
        if (personnage.getX() == x && personnage.getY() == y) {
            retour = PJ;
        } else if (sortie.getX() == x && sortie.getY() == y) {
            retour = SORTIE;
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
                case "haut":
                    suiv[0] = x - 1;
                case "bas":
                    suiv[0] = x + 1;
                case "gauche":
                    suiv[1] = y - 1;
                case "droite":
                    suiv[1] = y + 1;
                default:
                    throw new ActionInconnueException(direction);
            }


        } catch (ActionInconnueException e) {
            System.out.println("Action : "+ direction + " n'est pas valide");

        }
        return suiv;
    }


    public static Labyrinthe chargerLabyrinthe(String nom) {
        try {
            //ouverture des flux de lecture du fichier contenant le labyrinthe
            BufferedReader br = new BufferedReader(new FileReader(nom));
            int x = Integer.parseInt(br.readLine()); //nombre de colonnes
            int y = Integer.parseInt(br.readLine()); //nombre de lignes
            //initialisation des variables du labyrinthe rendu
            boolean[][] murs = new boolean[x][y];
            Personnage p = new Personnage(0, 0);
            Sortie s = new Sortie(0, 0);

            for (int i = 0; i < x; i++) {
                String ligne = br.readLine();
                for (int j = 0; j < y; j++) {
                    switch (ligne.charAt(j)) {
                        case 'X':
                            murs[i][j] = true;
                            break;
                        case 'S':
                            s.setX(i);
                            s.setY(j);
                            murs[i][j] = false;
                            break;
                        case 'P':
                            p.setX(i);
                            p.setY(j);
                            murs[i][j] = false;
                            break;
                        case '.':
                            murs[i][j] = false;
                            break;
                        default:
                            System.out.println("Erreur"); //TODO à completer
                    }
                }
            }
            br.close();
            return new Labyrinthe(murs, p, s);
        } catch (IOException e) {
            System.out.println("Erreur à l'ouverture du fichier");
        }
        return null;
    }


    /**
     * methode qui permet de deplacer le personnage en modifiant ses coordonees
     * @param action : chaine de caracteres
     */
    public void deplacerPerso(String action) {
        //on regarde la case suivante

        // si c'est un mur on s'arrete
        // quand le personnage s'arrete on regarde si il est sur la sortie en appellant la methode etre fini
    }

    public boolean etreFini() {
        return  this.personnage.getX() == this.sortie.getX() &&
                this.personnage.getY() == this.sortie.getY();
    }

    public String toString(){
        StringBuilder returnedString = new StringBuilder();

        for (int i = 0; i < this.murs.length; i++) {
            for (int j = 0; j < this.murs[0].length; j++){
                returnedString.append(getChar(i,j));
            }
            returnedString.append("\n");
        }

        return returnedString.toString();
    }

}
