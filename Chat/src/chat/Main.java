/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.net.MulticastSocket;

/**
 *
 * @author leonardo
 */
public class Main {

    private final Runnable listener = null;
    private final Runnable talker = null;
    private MulticastSocket multicastSocket = null;

    public static void main(String[] args) {
        String id_grupo = "225.1.2.3";
        int porta = 6789;
        String apelido = "apelido";
        String join = "JOIN";

        if (join.equals("JOIN")) {

            try {
                Client client = new Client(id_grupo, porta, apelido);
            } catch (NumberFormatException ex) {
                throw new RuntimeException("listenTOPort talkToHost talkToPort");
            }
        }
    }

}
