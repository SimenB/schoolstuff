package no.nith.pg5100.exam.task1.remote;

import no.nith.pg5100.exam.task1.interfaces.IClient;
import no.nith.pg5100.exam.task1.interfaces.IServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Simen Bekkhus
 */
public class ServerImpl extends UnicastRemoteObject implements IServer {
    private static final String nameOfServer = "Server";
    protected List<Client> clients = new ArrayList<>();

    protected ServerImpl() throws RemoteException {
    }

    @Override
    public void logIn(final IClient client, final String nick) throws RemoteException {
        clients.add(new Client(client, nick));

        this.broadcastMessage(nameOfServer, null, nick + " has joined the service!");
    }

    @Override
    public void logOut(final String nick) throws RemoteException {
        for (Client client : clients) {
            if (client.nickname.equals(nick)) {

                clients.remove(client);

                this.broadcastMessage(nameOfServer, null, nick + " has left the service!");

                break;
            }
        }
    }

    @Override
    public ArrayList<String> subscribeToWords(final String nick, final List<String> keywords) throws RemoteException {
        Client client = null;

        for (Client client1 : clients) {
            if (client1.nickname.equals(nick)) {
                client = client1;
            }
        }

        if (client == null) {
            throw new RuntimeException();
        }

        for (String keyword : keywords) {
            client.subscribe(keyword);
        }

        return (ArrayList<String>) client.subscribedWords;
    }

    @Override
    public void broadcastMessage(final String nick, final List<String> keywords, final String message) throws RemoteException {
        for (Client client : clients) {
            if (nick.equals(nameOfServer) || client.isSubscribed(keywords)) {
                client.client.displayMessage(nick, message);
            }
        }

        System.out.println("Message '" + message + "' sent from " + nick + " with the keywords: " + keywords.toString());
    }

    private class Client {
        private IClient client;
        private String nickname;
        private List<String> subscribedWords;

        private Client(final IClient client, final String nickname) {
            this.client = client;
            this.nickname = nickname;
            this.subscribedWords = new ArrayList<>();
        }

        private boolean isSubscribed(final List<String> keywords) {
            for (String subscribedWord : subscribedWords) {
                if (keywords.contains(subscribedWord)) {
                    return true;
                }
            }

            return false;
        }

        private void subscribe(String word) {
            if (!this.subscribedWords.contains(word)) {
                this.subscribedWords.add(word);
            }
        }
    }
}
