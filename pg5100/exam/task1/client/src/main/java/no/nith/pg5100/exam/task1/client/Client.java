package no.nith.pg5100.exam.task1.client;

import no.nith.pg5100.exam.task1.interfaces.Constants;
import no.nith.pg5100.exam.task1.interfaces.IServer;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 * @author Simen Bekkhus
 */
public class Client {
    private static Scanner scanner;

    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        Registry registry = LocateRegistry.getRegistry("localhost", Constants.RMI_PORT);

        IServer service = (IServer) registry.lookup(Constants.RMI_ID);

        scanner = new Scanner(System.in);

        System.out.print("Please enter your name: ");
        String name = scanner.nextLine();

        System.out.print("please enter the words you which to subscribe to (CSV): ");
        String[] words = scanner.nextLine().split(",");

        System.out.println();

        service.login(new ClientImpl(), name);

        service.subscribeToWords(name, words);

        messageloop(service, name);
    }

    private static void messageloop(final IServer service, final String name) throws RemoteException {
        for (; ; ) {
            System.out.print("Enter the keywords you want to use for your message (CSV): ");
            String[] words = scanner.nextLine().split(",");

            System.out.print("Enter your message: ");
            String message = scanner.nextLine();

            service.broadcastMessage(name, words, message);
        }
    }
}
