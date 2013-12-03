package no.nith.pg5100.exam.task1.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Simen Bekkhus
 */
public interface IServer extends Remote {
    void login(IClient client, String nick) throws RemoteException;

    List<String> subscribeToWords(String nick, String[] keywords) throws RemoteException;

    void broadcastMessage(String nick, String[] keywords, String message) throws RemoteException;
}
