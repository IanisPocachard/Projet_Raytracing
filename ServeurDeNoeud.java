import java.rmi.RemoteException;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;

import java.lang.Thread;

class ServeurDeNoeud implements InterfaceServeurDeNoeud {
    private List<InterfaceNoeudDeCalcul> noeuds; 
    
    private class EnvoieCalcul extends Thread {
      
      private InterfaceNoeudDeCalcul noeud
      private InterfaceCalcul calcul
      
      public EnvoieCalcul(InterfaceNoeudDeCalcul n, InterfaceCalcul c) {
        this.noeud = n;
        this.calcul = c;
      }
      
      public void run() {
        //appel de la méthode de calcul du noeud
      }
      
    }

    public ServeurDeNoeud() {
        this.noeuds = new ArrayList<>();
    }

    public void enregistrerNoeudDeCalcul(InterfaceNoeudDeCalcul n) throws RemoteException{
        this.noeuds.add(n);
    }

    public void supprimerNoeudDeCalcul(InterfaceNoeudDeCalcul n) throws RemoteException {
        this.noeuds.remove(c);
    }

    public void distribuerCalcul(InterfaceCalcul d) {
      //choisir un noeud disponible
      //envoyer dans un autre thread le calcul au noeud
    }

}
