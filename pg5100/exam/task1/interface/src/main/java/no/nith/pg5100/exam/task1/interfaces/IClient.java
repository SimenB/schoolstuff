package no.nith.pg5100.exam.task1.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Simen Bekkhus
 */
public interface IClient extends Remote {
    void displayMessage(String nick, String message) throws RemoteException;
}
