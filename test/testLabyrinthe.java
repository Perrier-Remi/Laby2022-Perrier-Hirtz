import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    public void testGetSuivant() {
        assertEquals(laby.getSuivant(1,1,"haut"),new int[] {0,1}, "la case en haut est en 0,1");
    }

}
