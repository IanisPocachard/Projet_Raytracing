import java.rmi.RemoteException;
import java.rmi.Remote;

interface InterfaceServeurDeNoeud implements remote  {
    public void enregistrerNoeudDeCalcul(InterfaceNoeudDeCalcul n) throws RemoteException;
    public void supprimerNoeudDeCalcul(InterfaceNoeudDeCalcul n) throws RemoteException;
    public void distribuerCalcul(Calcul d);

}
