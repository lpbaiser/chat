package chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author leonardo
 */
public class Cliente {

    private final Runnable listener;
    private final Runnable talker;
    private MulticastSocket multicastSocket = null;

    public Cliente(String idGrupo, int port, String apelido) {

        try {
            InetAddress group = InetAddress.getByName(idGrupo);

            /* cria um socket multicast */
            multicastSocket = new MulticastSocket(port);
            /* adiciona o host ao grupo */
            multicastSocket.joinGroup(group);
            this.listener = new Listener(multicastSocket);

            byte[] m = (apelido).getBytes();
            DatagramPacket messageOut = new DatagramPacket(m, m.length, multicastSocket.getInterface(), multicastSocket.getLocalPort());
            /* envia o datagrama como multicast */
            multicastSocket.send(messageOut);

            this.talker = new Talker(multicastSocket);
        } catch (SocketException | UnknownHostException ex) {
            throw new RuntimeException(ex.getMessage());
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        System.out.println("Digite o id do grupo:");
        String id_grupo = "225.1.2.3";
//        String id_grupo = in.next();
//        System.out.println("Digite a porta:");
//        int porta = in.nextInt();
        int porta = 6789;
//        System.out.println("Digite o apelido:");
//        String apelido = in.next();
        String apelido = "leo";
//        System.out.println("Digite JOIN para entrar no grupo:");
        String join = "JOIN";

        if (join.equals("JOIN")) {

            try {
                Cliente cliente = new Cliente(id_grupo, porta, apelido);
                Thread listener = new Thread(cliente.listener);
                Thread talker = new Thread(cliente.talker);
                talker.start();
                listener.start();
            } catch (NumberFormatException ex) {
                throw new RuntimeException("listenTOPort talkToHost talkToPort");
            }
        }

    }

}
