import java.rmi.RemoteException;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;

import java.lang.Thread;

class ServeurDeNoeud implements InterfaceServeurDeNoeud {
    private List<InterfaceNoeudDeCalcul> noeuds;
    private int index;

    public ServeurDeNoeud() {
        this.noeuds = new ArrayList<>();
        this.index = -1;
    }

    public void enregistrerNoeudDeCalcul(InterfaceNoeudDeCalcul n) throws RemoteException{
        this.noeuds.add(n);
    }

    public void supprimerNoeudDeCalcul(InterfaceNoeudDeCalcul n) throws RemoteException {
        this.noeuds.remove(n);
    }

    public InterfaceNoeudDeCalcul distribuerNoeudDisponible() throws RemoteException {
      if (this.noeuds.size() == 0) return null; 
      this.index++;
      if (this.index>=this.noeuds.size()) this.index = 0;
      
      System.out.println("Distribution du noeud index : " + this.index);

      return this.noeuds.get(this.index);
    }

}
