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
public class Listener implements Runnable {
    
    private final MulticastSocket multicastSocket;
    private byte[] buffer;
    private String apelido;

    public Listener(MulticastSocket multicastSocket, String apelido) {
        this.multicastSocket = multicastSocket;
        this.buffer = new byte[1000];
        this.apelido = apelido;
    }

    @Override
    public void run() {

    }

}
