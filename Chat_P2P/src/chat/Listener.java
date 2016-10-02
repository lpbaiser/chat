package chat;

import controller.User;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

/**
 *
 * @author leonardo
 */
public class Listener implements Runnable {

    private final MulticastSocket multicastSocket;
    private final InetAddress address;
    private byte[] buffer;
    private User user;

    public Listener(MulticastSocket multicastSocket, InetAddress address, User user) {
        this.multicastSocket = multicastSocket;
        this.address = address;
        this.user = user;
    }

    @Override
    public void run() {
        String mensagemStr;
        do {
            try {
                this.buffer = new byte[1000];
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                multicastSocket.receive(request);
                Message message = new Message();
                message.setText(new String(request.getData()));

            } catch (IOException ex) {
                System.err.println("Erro ao receber mensagem");
            }
        } while (true);

    }
    
  

}
