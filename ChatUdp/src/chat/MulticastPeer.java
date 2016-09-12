package chat;


/**
 * MulticastPeer: Implementa um peer multicast
 * Descricao: Envia uma mensagem para todos os membros do grupo. Apos tres msgs, finaliza.
 */
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class MulticastPeer {

    public static void main(String args[]) {
        /* args[0]: mensagem e args[1]: ip multicast (entre 224.0.0.0 a 239.255.255.255 */
        MulticastSocket s = null;
        try {

            Scanner in = new Scanner(System.in);
            System.out.println("Digite o id do grupo:");
            String id_grupo = in.next();
            System.out.println("Digite a porta:");
            int porta = in.nextInt();
            System.out.println("Digite o apelido:");
            String apelido = in.next();
            System.out.println("Digite JOIN para entrar no grupo:");

            InetAddress group = null;
            if (in.next().equals("JOIN")) {
                /* cria um grupo multicast */
                group = InetAddress.getByName(id_grupo);
                /* cria um socket multicast */
                s = new MulticastSocket(porta);
                /* adiciona o host ao grupo */
                s.joinGroup(group);
                /* cria um datagrama com a msg */
                byte[] m = (apelido + "entrou no grupo").getBytes();
                DatagramPacket messageOut = new DatagramPacket(m, m.length, group, porta);
                /* envia o datagrama como multicast */
                s.send(messageOut);
            }
            /* aguarda o recebimento de msgs de outros peers */
            byte[] buffer = new byte[1000];
            for (int i = 0; i < 3; i++) {
                DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);
                s.receive(messageIn);
                System.out.println("Recebido:" + new String(messageIn.getData()));
            }
            /* retira-se do grupo */
            s.leaveGroup(group);
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (s != null) {
                s.close(); //fecha o socket
            }
        } //finally
    } //main		      	
}//class
