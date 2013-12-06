package no.nith.pg5100.exam.task1.client;

import no.nith.pg5100.exam.task1.interfaces.Constants;
import no.nith.pg5100.exam.task1.interfaces.IServer;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

        service.logIn(new ClientImpl(), name);

        messageloop(service, name);
    }

    private static void messageloop(final IServer service, final String name) throws RemoteException {
        for (; ; ) {
            System.out.println("What do you want to do?");
            System.out.println("1: Publish a message");
            System.out.println("2: Subscribe to keywords");
            System.out.println("-1: Log out");

            final int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
            case 1:
                newMessage(service, name);
                break;
            case 2:
                subscribeToWords(service, name);
                break;
            case -1:
                service.logOut(name);
                System.exit(0);
            default:
                System.out.println("Please enter a valid option");
                break;
            }
        }
    }

    private static void subscribeToWords(final IServer service, final String name) throws RemoteException {
        System.out.print("Please enter the words you which to subscribe to (CSV): ");
        final List<String> keywords = getKeywords();
        final ArrayList<String> subscribedWords = service.subscribeToWords(name, keywords);

        System.out.println(name + " is now subscribed to " + subscribedWords.toString());
    }

    private static List<String> getKeywords() {
        String[] strings = scanner.nextLine().split(",");
        return Arrays.asList(strings);
    }

    private static void newMessage(final IServer service, final String name) throws RemoteException {
        System.out.print("Enter the keywords you want to use for your message (CSV): ");
        final List<String> keywords = getKeywords();

        System.out.print("Enter your message: ");
        String message = scanner.nextLine();

        service.broadcastMessage(name, keywords, message);
    }
}
