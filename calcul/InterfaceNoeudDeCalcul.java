package calcul;
import client.TacheCalcul;
import java.rmi.Remote;
import java.rmi.RemoteException;
import raytracer.Image;

public interface InterfaceNoeudDeCalcul extends Remote {
    public Image calculer(TacheCalcul calcul) throws RemoteException;
}
