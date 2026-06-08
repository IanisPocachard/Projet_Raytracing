package noeudcalcul;
import java.rmi.RemoteException;

interface InterfaceNoeudDeCalcul extends Remote {
    public Image calculer(TacheCalcul calcul) throws RemoteException;
}
