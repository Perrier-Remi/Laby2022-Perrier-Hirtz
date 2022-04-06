import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class testLabyrinthe {

    Labyrinthe laby;

    @BeforeEach
    public void chargerLesDonnee() {
        try {
            laby = laby.chargerLabyrinthe("laby/laby0.txt");
        } catch (FichierIncorrectException e) {
            System.out.println(e);
        }
    }

    @Test
    public void testChargerLabyrintheEtToString() {
        assertEquals("XXXXXXX\nXS....X\nX..P..X\nX.....X\nXXXXXXX\n", laby.toString(),"methode toString ou chargerLabyrinthe fausse");
    }

    @Test
    public void testMethodeGetChar() {
        assertEquals('X', laby.getChar(0,0));
        assertEquals('S', laby.getChar(1,1));
        assertEquals('.', laby.getChar(1,2));
        assertEquals('P', laby.getChar(2,3));
    }

    @Test
    public void testGetSuivant() throws ActionInconnueException {
        int[] resultatAttenduHaut = {0,1};
        int[] resultatAttenduBas = {2,1};
        int[] resultatAttenduGauche = {1,0};
        int[] resultatAttenduDroite = {1,2};
        assertArrayEquals(laby.getSuivant(1,1,"haut"),resultatAttenduHaut, "la case en haut est en 0,1");
        assertArrayEquals(laby.getSuivant(1,1,"bas"),resultatAttenduBas, "la case en bas est en 2,1");
        assertArrayEquals(laby.getSuivant(1,1,"gauche"),resultatAttenduGauche, "la case à gauche est en 1,0");
        assertArrayEquals(laby.getSuivant(1,1,"droite"),resultatAttenduDroite, "la case à Droite est en 1,2");
    }

    @Test
    public void testExceptionGetSuivant() {
        ActionInconnueException exception = assertThrows(
                ActionInconnueException.class,
                () -> {laby.getSuivant(1,1,"directionFausse");}
        );
    }

    @Test
    public void testMethodeDeplacerPerso() {
        //deplacement vers le haut
        laby.deplacerPerso("haut");
        assertEquals(laby.getChar(1,3), 'P', "le personnage doit etre en x 1 et y 3");

        //deplacement vers le bas
        laby.deplacerPerso("bas");
        assertEquals(laby.getChar(3,3), 'P', "le personnage doit etre en x 3 et y 3");

        //deplacement vers la gauche
        laby.deplacerPerso("gauche");
        assertEquals(laby.getChar(3,1), 'P', "le personnage doit etre en x 3 et y 1");

        //deplacement vers la droite
        laby.deplacerPerso("droite");
        assertEquals(laby.getChar(3,5), 'P', "le personnage doit etre en x 3 et y 1");
    }

    @Test
    public void testMethodeEtreFini() {
        assertEquals(laby.etreFini(), false, "le personnage n'est pas sur la case sortie");
        laby.deplacerPerso("haut");
        laby.deplacerPerso("gauche");
        assertEquals(laby.etreFini(), true, "le personnage et la sortie sont sur la même case donc etreFini() doit renvoyer vrai");
    }

    @Test
    public void testExceptionChargerLabyrintheDeuxSorties() {
        FichierIncorrectException exception = assertThrows(
                FichierIncorrectException.class,
                () -> {laby.chargerLabyrinthe("laby/laby_deuxSortie.txt");}
        );
    }

    @Test
    public void testExceptionChargerLabyrinthePasSorties() {
        FichierIncorrectException exception = assertThrows(
                FichierIncorrectException.class,
                () -> {laby.chargerLabyrinthe("laby/laby_pasSortie.txt");}
        );
    }

    @Test
    public void testExceptionChargerLabyrintheDeuxPersonnages() {
        FichierIncorrectException exception = assertThrows(
                FichierIncorrectException.class,
                () -> {laby.chargerLabyrinthe("laby/laby_deuxPersonnages.txt");}
        );
    }

    @Test
    public void testExceptionChargerLabyrinthePasPersonnages() {
        FichierIncorrectException exception = assertThrows(
                FichierIncorrectException.class,
                () -> {laby.chargerLabyrinthe("laby/laby_pasPersonnages.txt");}
        );
    }
}
