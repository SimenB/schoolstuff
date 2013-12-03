package no.nith.pg5100.exam.task1.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Simen Bekkhus
 */
public interface IServer extends Remote {
    boolean subscribeToWord(String keyword) throws RemoteException;
}
