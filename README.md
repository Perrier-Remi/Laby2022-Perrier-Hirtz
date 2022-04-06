#   -- SAE Labyrinthe --

Projet réalisé par **Rémi Perrier** et **Jules Hirtz**

Professeur : **Vincent Thomas**

Difficultés : la difficulté majeure du projet est que Jules Hirtz est tombé
malade d'une bronchite avec presque 40°C de fièvre, il s'est retrouvé dans l'incapacité 
totale de pouvoir travailler pendant les 10 derniers jours du projet. J'ai (Rémi) fait une grande partie
de ce projet seul. Le manque de temps est aussi la conséquence majeure de ce problème.
Néamoins le projet est fini à temps et est fonctionnel. On a rencontré quelques difficultés sur le déplacement de personnage et
du chargement du labyrinthe, mais ces problèmes ont vite été résolus.

Utilisation de l'application : le jeu commence par demander
l'emplacement du labyrinthe. À partir de ce moment-là vous n'avez que 5 actions possibles jusqu'à la fin de la partie
(résolution du niveau ou interruption du jeu par le joueur) : 
- haut : vous permet de vous déplacer vers le haut jusqu'au prochain mur
- bas : vous permet de vous déplacer vers le bas jusqu'au prochain mur
- gauche : vous permet de vous déplacer vers la gauche jusqu'au prochain mur
- droite : vous permet de vous déplacer vers la droite jusqu'au prochain mur
- exit : permet de terminer la partie quelque soit l'avancement de celle-ci

Test : tous nos tests répondent correctement à nos attentes. Il n'y a rien à signaler sur ce point.

Couverture des tests : chaque méthode est testée avec des valeurs type vérifiant ainsi leur bon fonctionnement.
Les méthodes devant retourner des exceptions, lèvent bien des exceptions lorsque les valeurs entrées sont fausses,
c'est notamment le cas de chargerLabyrinthe qui peut renvoyer beaucoup d'exceptions, car il existe de nombreuses manières pour que le fichier contenant le labyrinthe ne soit pas correct. L'autre méthode qui peut lever des exceptions est getSuivant lorsque la valeur entrée n'est pas celle attendue.
Ces deux méthodes sont largement testées pour être sûr de leurs efficacités.