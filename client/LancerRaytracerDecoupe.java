package client;

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

        // Nombre de découpages
        int n = 2;
        
        if(args.length > 0){
            fichier_description = args[0];
            if(args.length > 1){
                largeur = Integer.parseInt(args[1]);
                if(args.length > 2)
                    hauteur = Integer.parseInt(args[2]);
                    if(args.length > 3)
                        n = Integer.parseInt(args[3]);
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
        
        int largeurBloc = largeur / n;
        int hauteurBloc = hauteur / n;

        System.out.println("Début du rendu par sous-images...");
        Instant debut = Instant.now();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                
                // Coordonnées du coin en haut à gauche du bloc courant
                int x0 = i * largeurBloc;
                int y0 = j * hauteurBloc;
                
                // Calcul de la taille du bloc. 
                // Si c'est le dernier bloc (i ou j == n - 1), on prend tout le reste 
                // pour éviter les pixels vides à cause des divisions entières.
                int l = (i == n - 1) ? (largeur - x0) : largeurBloc;
                int h = (j == n - 1) ? (hauteur - y0) : hauteurBloc;

                System.out.println(" -> Calcul du bloc [" + i + "][" + j + "] à (" + x0 + "," + y0 + ") taille " + l + "x" + h);
                
                // Calcul de la sous-image
                Image imagePartielle = scene.compute(x0, y0, l, h);
                
                // Affichage direct du bloc sur la fenêtre
                disp.setImage(imagePartielle, x0, y0);
            }
        }

        Instant fin = Instant.now();
        long duree = Duration.between(debut, fin).toMillis();
        
        System.out.println("Images partielles calculées en : " + duree + " ms");
    }	
}
