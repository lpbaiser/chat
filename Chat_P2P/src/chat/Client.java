package chat;

import controller.User;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author leonardo
 */
public class Client {

    private Runnable listener;
    private Runnable talker;

    public void joinGroup(User user) {
        MulticastSocket multicastSocket = null;
        try {
            InetAddress group = InetAddress.getByName("255.1.2.3");

            //cria um socket multicast
            multicastSocket = new MulticastSocket(user.getPort());

            //adiciona o host ao grupo
            multicastSocket.joinGroup(group);
            
            //Instancia a classe Listener com o multicastSocket e o apelido do cliente
            this.listener = new Listener(multicastSocket, group, user);

            //Instancia a classe Talker com o multicastSocket e o apelido do cliente
            this.talker = new Talker(multicastSocket, group, user);

            Thread l = new Thread(this.listener);
            Thread t = new Thread(this.talker);
            t.start();
            l.start();

        } catch (SocketException | UnknownHostException ex) {
            throw new RuntimeException(ex.getMessage());
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }

    }

    public void leaveGroup(User user) {
        MulticastSocket multicastSocket = null;
        try {
            InetAddress group = InetAddress.getByName(user.getIp());
            multicastSocket = new MulticastSocket(user.getPort());
            multicastSocket.leaveGroup(group);
        } catch (IOException ex) {
            System.err.println("Erro ao sair do chat");
        } finally {
            if (multicastSocket != null) {
                multicastSocket.close(); //close socket
            }
        }
    }

}
