# Projet Raytracing

## Lien du repo git :
https://github.com/IanisPocachard/Projet_Raytracing/

## Membres du groupe :
- Ambroise Gilbert
- Ianis Pocachard
- Adrien Tritarelli
- Louis Ardhuin
- Tolkacheva Anastasia

## Objectif du projet
Accélérer le raytracing en divisant l’image en blocs calculés par plusieurs noeuds.


## Architecture

Le projet est organisé autour de plusieurs modules :

- `raytracer/` : classes fournies pour le rendu de l'image
- `calcul/` : nœuds de calcul distants
- `serveur_central/` : serveur RMI qui distribue les nœuds disponibles
- `client/` : client de rendu distribué



## Fonctionnement
- démarrage du registre : `rmiregistry`
- démarrage du serveur central : `java serveur_central/LancerServeurDeNoeud` 
- enregistrement des nœuds : `java calcul/LancerNoeudDeCalcul`
- lancement du client : `java client/LancerRayTracerDecoupe` 
- découpage de l’image
- lancement des threads 
- récupération des imagettes

## Interfaces du projet

### Interface du serveur central

```java
package serveur_central;

import java.rmi.Remote;
import java.rmi.RemoteException;
import calcul.InterfaceNoeudDeCalcul;

public interface InterfaceServeurDeNoeud extends Remote {
    public void enregistrerNoeudDeCalcul(InterfaceNoeudDeCalcul n)
        throws RemoteException;

    public void supprimerNoeudDeCalcul(InterfaceNoeudDeCalcul n)
        throws RemoteException;

    public InterfaceNoeudDeCalcul distribuerNoeudDisponible()
        throws RemoteException;
}
```

Cette interface décrit le service central. Elle permet :

- à un nœud de calcul de s'enregistrer ;
- au client de demander un nœud ;
- au client de demander la suppression d'un nœud qui ne répond plus.

### Interface d'un nœud de calcul

```java
package calcul;

import client.TacheCalcul;
import java.rmi.Remote;
import java.rmi.RemoteException;
import raytracer.Image;

public interface InterfaceNoeudDeCalcul extends Remote {
    public Image calculer(TacheCalcul calcul) throws RemoteException;
}
```

Cette interface décrit ce qu'un nœud de calcul sait faire : recevoir une tâche de calcul et retourner une image partielle.

Dans les deux cas, `Remote` sert à indiquer qu'une interface est une interface distante RMI. Cela veut dire que les méthodes de cette interface peuvent être appelées depuis une autre JVM donc potentiellement sur une autre machine.





## Commandes d’exécution
 
Compiler le projet :
- Se mettre à la racine du projet
- Exécuter la commande : `javac *.java calcul/*.java client/*.java raytracer/*.java serveur_central/*.java`

## Schémas

### Diagramme de l'architecture de l'application répartie

![schemas de l'application](Doc/DiagrammeArchiFinal.png)


Ce schémas décrit les différentes étapes et acteurs.

Client:
   - récupère le service central en passant par l'annuaire
   - découpe l'image, pour chaque morceau d'image demande un nœud au service central, crée un thread pour utiliser ce nœud pour calculer le bout d'image
   - demande un nœud au service central
   - fait travailler le nœud de calcul pour qu'il calcule l'image

Service Central:
   - possède une liste de nœuds de calcul
   - donne au client un nœud disponible

nœud de calcul:
   - calcule l'image


Explication du fonctionnement plus finement :
1. L'annuaire RMI démarre.
2. Le serveur central crée un objet `ServeurDeNoeud`.
3. Le serveur central exporte cet objet et l'enregistre sous le nom `ServiceCentral`.
4. Chaque nœud crée un objet `NoeudDeCalcul`.
5. Chaque nœud exporte son objet.
6. Chaque nœud récupère `ServiceCentral` dans l'annuaire.
7. Chaque nœud envoie sa référence distante au serveur central.
8. Le client récupère le service central une référence distante du `ServeurDeNoeud`.
9. Le client découpe l'image en n * n blocs.
10. Pour chaque bloc, le client demande un nœud au serveur central.
11. Le client crée un thread `EnvoyerCalcul`.
12. Chaque thread appelle `noeud.calculer(tache)`.
13. Le nœud calcule son imagette avec `scene.compute(...)`.
14. Le nœud renvoie une `Image`.
15. Le client affiche cette image partielle au bon endroit.
16. Enfin, le client attend tous les threads qu'il a lancé avec `join()`.


### courbes comparatives de la vitesse de chargement de l'image avec et sans répartition
![schemas de l'application](Doc/Courbes.png)
la répartition permet d'accélérer le rendu de l'image

## Animation
[![video de démo](Doc/Miniature.png)](https://raw.githubusercontent.com/IanisPocachard/Projet_Raytracing/refs/heads/main/video.webm)
