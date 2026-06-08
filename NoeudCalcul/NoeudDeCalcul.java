package NoeudCalcul;
import java.rmi.RemoteException;

public class NoeudDeCalcul implements InterfaceNoeudDeCalcul {
    public Image calculer(TacheCalcul calcul) throws RemoteException {
        Scene scene = calcul.getScene();
        return scene.compute(calcul.getX(), calcul.getY(), calcul.getLargeur(), calcul.getHauteur());
    }
}
