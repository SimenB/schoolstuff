package no.nith.pg5100.exam.task1.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Simen Bekkhus
 */
public interface IServer extends Remote {
    void logIn(IClient client, String nick) throws RemoteException;

    void logOut(String nick) throws RemoteException;

    ArrayList<String> subscribeToWords(String nick, List<String> keywords) throws RemoteException;

    void broadcastMessage(String nick, List<String> keywords, String message) throws RemoteException;
}
