import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class testLabyrinthe {

    @Test
    public void testChargerLabyrinthe() {
        Labyrinthe l = new Labyrinthe();
        l.chargerLabyrinthe("laby/laby0.txt");
        assertEquals(true, true,"messqge random pour le moment");
    }

}