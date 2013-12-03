package no.nith.pg5100.exam.task1.client;

import no.nith.pg5100.exam.task1.interfaces.IClient;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author Simen Bekkhus
 */
public class ClientImpl extends UnicastRemoteObject implements IClient {
    protected ClientImpl() throws RemoteException {
    }

    @Override
    public void displayMessage(String nick, String message) throws RemoteException {
        System.out.println("\n" + nick + ": " + message);
    }
}
