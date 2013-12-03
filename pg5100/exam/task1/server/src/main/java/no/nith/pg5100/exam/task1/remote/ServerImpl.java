package no.nith.pg5100.exam.task1.remote;

import no.nith.pg5100.exam.task1.interfaces.IServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author Simen Bekkhus
 */
public class ServerImpl extends UnicastRemoteObject implements IServer {
    @Override
    public boolean subscribeToWord(String keyword) throws RemoteException {
        return keyword.length() > 5;
    }

    public ServerImpl() throws RemoteException {
    }
}
