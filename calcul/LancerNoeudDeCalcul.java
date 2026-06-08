package calcul;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.rmi.registry.Registry;

import serveur_central.InterfaceServeurDeNoeud;

public class LancerNoeudDeCalcul {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        NoeudDeCalcul noeudCalcul = new NoeudDeCalcul();
        
        InterfaceNoeudDeCalcul rd = (InterfaceNoeudDeCalcul) UnicastRemoteObject.exportObject(noeudCalcul, 0);
        
        
        String ip = (args.length > 0) ? args[0] : "localhost"; 
        Registry reg = LocateRegistry.getRegistry(ip);
        
        InterfaceServeurDeNoeud serviceCentral = (InterfaceServeurDeNoeud) reg.lookup("ServiceCentral");
        
        // envoi du noeud de calcul sur le service central
        serviceCentral.enregistrerNoeudDeCalcul(rd);

        System.out.println("Noeud de calcul enregistré sur le service central et donc en attente de tâches de calcul");
        
        
    }
}
