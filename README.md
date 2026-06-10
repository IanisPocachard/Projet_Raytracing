# Projet Raytracing

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


### courbes comparatives de la vitesse de chargement de l'image avec et sans répartition
![schemas de l'application](Doc/Courbes.png)
la répartition permet d'accélérer le rendu de l'image

## Animation
[![video de démo](Doc/Miniature.png)](https://raw.githubusercontent.com/IanisPocachard/Projet_Raytracing/refs/heads/main/video.webm)
