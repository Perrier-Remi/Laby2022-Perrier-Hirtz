public class Labyrinthe {
    private boolean[][] murs;
    private Personnage personnage;
    private Sortie sortie;


    public char getChar(int x, int y){
        char retour = '.';
        if(murs[x][y] == false) {
            retour = 'X';
        } else if (personnage.getX() == x && personnage.getY() == y) {
            retour = 'P';
        } else if (sortie.getX() == x && sortie.getY() == y) {
            retour = 'S';
        }
        return retour;
    }

}
