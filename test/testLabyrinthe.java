import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class testLabyrinthe {

    @Test
    public void testChargerLabyrinthe() {
        Labyrinthe l = new Labyrinthe();
        l = l.chargerLabyrinthe("laby/laby0.txt");
        System.out.println(l);
        assertEquals(true, true,"messqge random pour le moment");
    }

}
