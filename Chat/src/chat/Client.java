/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author leonardo
 */
public class Client {

    private MulticastSocket multicastSocket;
    private final Runnable listener;
    private final Runnable talker;

    public Client(String idGroup, int port, String apelido) {

        try {
            InetAddress group = InetAddress.getByName(idGroup);

            //cria um socket multicast
            multicastSocket = new MulticastSocket(port);

            //adiciona o host ao grupo
            multicastSocket.joinGroup(group);
            //Instancia a classe Listener com o multicastSocket e o apelido do cliente
            this.listener = new Listener(multicastSocket, apelido);

            //Instancia a classe Talker com o multicastSocket e o apelido do cliente
            this.talker = new Talker(multicastSocket, apelido);

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

}
