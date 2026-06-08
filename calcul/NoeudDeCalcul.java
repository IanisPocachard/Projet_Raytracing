package calcul;
import client.TacheCalcul;
import java.rmi.RemoteException;
import raytracer.Image;
import raytracer.Scene;

public class NoeudDeCalcul implements InterfaceNoeudDeCalcul {
    public Image calculer(TacheCalcul calcul) throws RemoteException {
        Scene scene = calcul.getScene();
        return scene.compute(calcul.getX(), calcul.getY(), calcul.getLargeur(), calcul.getHauteur());
    }
}
