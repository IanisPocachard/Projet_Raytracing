import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class LancerServeurDeNoeud {
    public static void main(String[] args) throws RemoteException {

        ServeurDeNoeud serviceCentral = new ServeurDeNoeud();

        InterfaceServeurDeNoeud rd = (InterfaceServeurDeNoeud) UnicastRemoteObject.exportObject(serviceCentral, 0);

        Registry reg = LocateRegistry.getRegistry(1099);

        reg.rebind("ServiceCentral", rd);

        System.out.println("Serveur de noeuds lancé");
        System.out.println("Service enregistré sous le nom : ServiceCentral");
    }
}