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
    public static int[] getSuivant(int x, int y, String direction) throws ActionInconnueException{
        int[] suiv = {x, y};
        switch (direction) {
            case "haut":
                suiv[0] = x - 1;
                break;
            case "bas":
                suiv[0] = x + 1;
                break;
            case "gauche":
                suiv[1] = y - 1;
                break;
            case "droite":
                suiv[1] = y + 1;
                break;
            default:
                throw new ActionInconnueException("direction : "+direction+" inconnu");
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
            //fermeture des flux
            br.close();
            return new Labyrinthe(murs, p, s);
        } catch (IOException e) {
            System.out.println("Erreur à l'ouverture du fichier");
        }
        //retourne null si il y a eu une erreur ou une exception lors de la génération du labyrinthe
        return null;
    }


    /**
     * methode qui permet de deplacer le personnage en modifiant ses coordonees
     * @param action : chaine de caracteres
     */
    public void deplacerPerso(String action) {
        //TODO à modifier lorque l'on rencontre la sortie
        boolean murRencontre = false;
        //tant que etreFini() est faux ou qu'on ne rencontre pas de mur, on continue
        while (!murRencontre) {
            int[] coorSuivantes = new int[2];
            char caseSuivante;
            try {
                // on regarde la case suivante
                coorSuivantes = getSuivant(this.personnage.getX(), this.personnage.getY(), action);
                caseSuivante = getChar(coorSuivantes[0],coorSuivantes[1]);
                if (caseSuivante == MUR) {
                    // si c'est un mur on s'arrete et on arrête la boucle
                    murRencontre = true;
                } else if (caseSuivante == VIDE) {
                    // si c'est une case vide alors on peut continuer
                    this.personnage.setX(coorSuivantes[0]);
                    this.personnage.setY(coorSuivantes[1]);
                }

            } catch (ActionInconnueException e) {
                System.out.println(e);
            }

        }
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
