package serveur_central;

import java.rmi.RemoteException;
import java.rmi.Remote;
import calcul.InterfaceNoeudDeCalcul;


public interface InterfaceServeurDeNoeud extends Remote  {
    public void enregistrerNoeudDeCalcul(InterfaceNoeudDeCalcul n) throws RemoteException;
    public void supprimerNoeudDeCalcul(InterfaceNoeudDeCalcul n) throws RemoteException;
    public InterfaceNoeudDeCalcul distribuerNoeudDisponible() throws RemoteException;
}
