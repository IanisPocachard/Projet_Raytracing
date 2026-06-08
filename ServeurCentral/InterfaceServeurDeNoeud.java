import NoeudCalcul.InterfaceNoeudDeCalcul;
import java.rmi.RemoteException;
import java.rmi.Remote;


interface InterfaceServeurDeNoeud extends Remote  {
    public void enregistrerNoeudDeCalcul(InterfaceNoeudDeCalcul n) throws RemoteException;
    public void supprimerNoeudDeCalcul(InterfaceNoeudDeCalcul n) throws RemoteException;
    public InterfaceNoeudDeCalcul distribuerNoeudDisponible() throws RemoteException;
}
