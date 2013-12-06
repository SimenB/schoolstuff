package no.nith.pg5100.exam.task1.remote;

import no.nith.pg5100.exam.task1.interfaces.Constants;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author Simen Bekkhus
 */
public class Server {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        ServerImpl messageService = new ServerImpl();

        Registry registry = LocateRegistry.createRegistry(Constants.RMI_PORT);
        registry.bind(Constants.RMI_ID, messageService);

        System.out.println("Service created");
    }
}
