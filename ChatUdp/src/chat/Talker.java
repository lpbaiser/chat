package chat;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
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
public class Talker implements Runnable {

    private final MulticastSocket multicastSocket;
    private byte[] buffer;

    public Talker(MulticastSocket multicastSocket) {
        this.multicastSocket = multicastSocket;
        this.buffer = new byte[1000];
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Digite a mensagem: ");
                String input = scanner.nextLine();
                buffer = input.getBytes();
                DatagramPacket request = new DatagramPacket(buffer, input.length(), multicastSocket.getInterface(), multicastSocket.getLocalPort());
                multicastSocket.send(request);
            } catch (IOException ex) {
                System.err.println("Erro ao criar pacote");
            }
        }
    }

}
