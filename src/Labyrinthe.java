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
        //si il y a un mur en x, y alors retour vaut MUR sinon il vaut VIDE
        char retour = murs[x][y] ? MUR : VIDE;
        //si il y a un personnage en x,y alors retour est changé et vaut PJ
        if (personnage.getX() == x && personnage.getY() == y) {
            retour = PJ;
            //si il y une sortie en x,y alors retour est changé et vaut SORTIE
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
     * @return case suivante
     */
    public static int[] getSuivant(int x, int y, String direction) throws ActionInconnueException{
        switch (direction) {
            case "haut":
                x--;
                break;
            case "bas":
                x++;
                break;
            case "gauche":
                y--;
                break;
            case "droite":
                y++;
                break;
            default:
                throw new ActionInconnueException("direction : "+direction+" inconnue");
        }
        return new int[] {x, y};
    }

    //todo gérer l'exception avec le nombre de murs incorrect
    public static Labyrinthe chargerLabyrinthe(String nom) throws FichierIncorrectException, IOException {
        //ouverture des flux de lecture du fichier contenant le labyrinthe
        BufferedReader br = new BufferedReader(new FileReader(nom));
        int x = Integer.parseInt(br.readLine()); //nombre de colonnes
        int y = Integer.parseInt(br.readLine()); //nombre de lignes
        //initialisation des variables du labyrinthe rendu
        boolean[][] murs = new boolean[x][y];
        Personnage p = new Personnage(0, 0);
        Sortie s = new Sortie(0, 0);
        //deux variables booléennes vérifient qu'il n'y ait qu'un et un seul personnage et qu'une et une seule sortie
        boolean flagSortie = false;
        boolean flagPersonnage = false;

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
                        if (flagSortie) {
                            throw new FichierIncorrectException("le fichier contient deux sorties");
                        } else {
                            flagSortie = true;
                        }
                        murs[i][j] = false;
                        break;
                    case 'P':
                        p.setX(i);
                        p.setY(j);
                        if (flagPersonnage) {
                            throw new FichierIncorrectException("le fichier contient deux personnages");
                        } else {
                            flagPersonnage = true;
                        }
                        murs[i][j] = false;
                        break;
                    case '.':
                        murs[i][j] = false;
                        break;
                    default:
                        throw new FichierIncorrectException("caractère : " + ligne.charAt(j) + " inconnu");
                }
            }
        }
        if (!flagSortie) {
            throw new FichierIncorrectException("le fichier ne contient aucunes sorties");
        }
        if (!flagPersonnage) {
            throw new FichierIncorrectException("le fichier ne contient aucuns personnages");
        }
        //fermeture des flux
        br.close();
        return new Labyrinthe(murs, p, s);
    }


    /**
     * methode qui permet de deplacer le personnage en modifiant ses coordonees
     * @param action : chaine de caracteres
     */
    public void deplacerPerso(String action) throws ActionInconnueException {
        boolean murRencontre = false;
        //tant que etreFini() est faux ou qu'on ne rencontre pas de mur, on continue
        while (!murRencontre) {
            int[] coorSuivantes = new int[2];
            char caseSuivante;
            // on regarde la case suivante
            coorSuivantes = getSuivant(this.personnage.getX(), this.personnage.getY(), action);
            caseSuivante = getChar(coorSuivantes[0],coorSuivantes[1]);
            // si c'est un mur on s'arrete et on arrête la boucle
            if (caseSuivante == MUR) {
                murRencontre = true;
                // si c'est une case vide ou la case sortie alors on peut continuer
            } else if (caseSuivante == VIDE || caseSuivante == SORTIE) {
                this.personnage.setX(coorSuivantes[0]);
                this.personnage.setY(coorSuivantes[1]);
            }
        }
    }

    public boolean etreFini() {
        return  this.personnage.getX() == this.sortie.getX() &&
                this.personnage.getY() == this.sortie.getY();
    }

    @Override
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
