package client;

import calcul.InterfaceNoeudDeCalcul;
import java.rmi.RemoteException;
import raytracer.*;

class EnvoyerCalcul extends Thread {

    private TacheCalcul calcul;
    private Disp disp;
    private InterfaceNoeudDeCalcul noeud;

    public EnvoyerCalcul(int x0, int y0, int l, int h, Scene s, Disp d, InterfaceNoeudDeCalcul n) {
        this.calcul = new TacheCalcul(s, x0, y0, l, h);
        this.disp = d;
        this.noeud = n;
    }

    public void run() {
        try {
            this.disp.setImage(this.noeud.calculer(this.calcul), this.calcul.getX(), this.calcul.getY());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
