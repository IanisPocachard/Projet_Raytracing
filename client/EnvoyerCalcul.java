package client;

import calcul.InterfaceNoeudDeCalcul;
import serveur_central.InterfaceServeurDeNoeud;
import java.rmi.RemoteException;
import java.rmi.ConnectException;
import raytracer.*;

class EnvoyerCalcul extends Thread {

    private TacheCalcul calcul;
    private Disp disp;
    private InterfaceNoeudDeCalcul noeud;
    private InterfaceServeurDeNoeud serveur;

    public EnvoyerCalcul(int x0, int y0, int l, int h, Scene s, Disp d, InterfaceNoeudDeCalcul n, InterfaceServeurDeNoeud serveur) {
        this.calcul = new TacheCalcul(s, x0, y0, l, h);
        this.disp = d;
        this.noeud = n;
        this.serveur = serveur;
    }

    @Override
    public void run() {
        try {
            this.disp.setImage(this.noeud.calculer(this.calcul), this.calcul.getX(), this.calcul.getY());
        } catch (ConnectException e) {
            System.out.println("Noeud inaccessible : " + e.getMessage());
            try {
                this.serveur.supprimerNoeudDeCalcul(noeud);
                this.noeud = this.serveur.distribuerNoeudDisponible();
            } catch (Exception ex) {
                System.out.println("Récupération d'un nouveau noeud impossible : " + ex.getMessage());
            }
        } catch (RemoteException e) {
            System.out.println("Erreur RMI : " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
