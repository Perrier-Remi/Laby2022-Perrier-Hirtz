import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class testLabyrinthe {

    Labyrinthe laby;

    @BeforeEach
    public void chargerLesDonnee() {
        laby = laby.chargerLabyrinthe("laby/laby0.txt");
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

}
