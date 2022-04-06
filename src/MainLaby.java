import java.io.IOException;
import java.util.Scanner;

public class MainLaby {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Entrez le chemin du fichier vers le labyrinthe : ");
        String nomFichier = sc.nextLine();
        System.out.println();

        try {
            Labyrinthe laby = Labyrinthe.chargerLabyrinthe(nomFichier);

            //affichage du labyrinthe
            System.out.println(laby);

            String action = "";
            //tant que l'action est différente de exit ou que le joueur ne se trouve pas sur la sortie, le jeu continu
            while (action != "exit" && !laby.etreFini()) {
                System.out.print("quelle action voulez vous effectuer ? ");
                action = sc.nextLine();

                try {
                    laby.deplacerPerso(action);
                } catch (ActionInconnueException e) {
                    System.out.println(e.getMessage());
                    System.out.println("l'action n'a pas été prise en compte");
                }

                System.out.println();
                System.out.println(laby);
            }

            if (laby.etreFini()) {
                System.out.println("   ________________________________________________________   ");
                System.out.println("   !!!   FELICITATION VOUS AVEZ TERMINE LE LABYRINTHE   !!!   ");
                System.out.println("   ________________________________________________________   ");
            }
        } catch (FichierIncorrectException e) {
            System.out.println(e.getMessage() + ", fermeture du programme");
        } catch (IOException e) {
            System.out.println("Le fichier n'existe pas, fermeture du programme");
        }


    }
}
