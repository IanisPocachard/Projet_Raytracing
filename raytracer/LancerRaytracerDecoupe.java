import java.time.Instant;
import java.time.Duration;

import raytracer.Disp;
import raytracer.Scene;
import raytracer.Image;

public class LancerRaytracerDecoupe {

    public static String aide = "Raytracer : synthèse d'image par lancé de rayons (https://en.wikipedia.org/wiki/Ray_tracing_(graphics))\n\nUsage : java LancerRaytracer [fichier-scène] [largeur] [hauteur]\n\tfichier-scène : la description de la scène (par défaut simple.txt)\n\tlargeur : largeur de l'image calculée (par défaut 512)\n\thauteur : hauteur de l'image calculée (par défaut 512)\n";
     
    public static void main(String args[]){

        // Le fichier de description de la scène si pas fournie
        String fichier_description="simple.txt";

        // largeur et hauteur par défaut de l'image à reconstruire
        int largeur = 512, hauteur = 512;
        
        if(args.length > 0){
            fichier_description = args[0];
            if(args.length > 1){
                largeur = Integer.parseInt(args[1]);
                if(args.length > 2)
                    hauteur = Integer.parseInt(args[2]);
            }
        }else{
            System.out.println(aide);
        }
        
   
        // création d'une fenêtre 
        Disp disp = new Disp("Raytracer", largeur, hauteur);
        
        // Initialisation d'une scène depuis le modèle 
        Scene scene = new Scene(fichier_description, largeur, hauteur);
        
        // Calcul de l'image de la scène les paramètres : 
        // - x0 et y0 : correspondant au coin haut à gauche
        // - l et h : hauteur et largeur de l'image calculée
        // Ici on calcule toute l'image (0,0) -> (largeur, hauteur)
        
        int demiLargeur = largeur / 2;
        int demiHauteur = hauteur / 2;

        System.out.println("Début du rendu par sous-images...");
        Instant debut = Instant.now();

        // 1. Calcul et affichage du coin Haut-Gauche
        // Origine : (0, 0), Taille : demiLargeur x demiHauteur
        System.out.println(" -> Calcul du coin Haut-Gauche");
        Image imageHautGauche = scene.compute(0, 0, demiLargeur, demiHauteur);
        disp.setImage(imageHautGauche, 0, 0);

        // 2. Calcul et affichage du coin Bas-Droit
        // Origine : (demiLargeur, demiHauteur), Taille : largeur restante x hauteur restante
        System.out.println(" -> Calcul du coin Bas-Droit");
        int resteLargeur = largeur - demiLargeur;
        int resteHauteur = hauteur - demiHauteur;
        Image imageBasDroit = scene.compute(demiLargeur, demiHauteur, resteLargeur, resteHauteur);
        disp.setImage(imageBasDroit, demiLargeur, demiHauteur);

        Instant fin = Instant.now();
        long duree = Duration.between(debut, fin).toMillis();
        
        System.out.println("Images partielles calculées en : " + duree + " ms");
    }	
}
