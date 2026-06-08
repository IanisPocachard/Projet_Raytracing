package NoeudCalcul;

import java.rmi.RemoteException;
import java.rmi.Remote;

interface InterfaceNoeudDeCalcul extends Remote {
    public Image calculer(TacheCalcul calcul) throws RemoteException;
}
