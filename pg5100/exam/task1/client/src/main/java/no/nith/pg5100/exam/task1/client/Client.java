package no.nith.pg5100.exam.task1.client;

import no.nith.pg5100.exam.task1.interfaces.Constants;
import no.nith.pg5100.exam.task1.interfaces.IServer;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author Simen Bekkhus
 */
public class Client {
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        Registry registry = LocateRegistry.getRegistry("localhost", Constants.RMI_PORT);

        IServer service = (IServer) registry.lookup(Constants.RMI_ID);
        boolean svar = service.subscribeToWord("hallaballa");

        System.out.println(svar);
    }
}
