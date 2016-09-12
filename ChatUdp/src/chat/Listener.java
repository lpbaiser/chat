package chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author leonardo
 */
public class Listener implements Runnable {

    private final MulticastSocket multicastSocket;
    private byte[] buffer;

    public Listener(MulticastSocket multicastSocket) {
        this.multicastSocket = multicastSocket;
        this.buffer = new byte[1000];
    }

    @Override
    public void run() {
        System.out.println("Aguardando mensagens...");
        DatagramPacket request = new DatagramPacket(buffer, buffer.length);
        String apelido = "";
        try {
            multicastSocket.receive(request);
            apelido = new String(request.getData());
            System.out.println(apelido + " entrou no grupo");
        } catch (IOException ex) {
            System.err.println("Erro ao ler pacote");
        }
        while (true) {
            buffer = new byte[1000];
            request = new DatagramPacket(buffer, buffer.length);
            try {
                multicastSocket.receive(request);
                String msg = new String(request.getData());
                System.out.print("[" + apelido + "]");
                System.out.println(msg);
            } catch (IOException ex) {
                System.err.println("Erro ao ler pacote");
            }
        }
    }

}
