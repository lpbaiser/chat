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
public class Talker implements Runnable {
    
    private final MulticastSocket multicastSocket;
    private InetAddress address;
    private byte[] buffer;
    private User user;

    public Talker(MulticastSocket multicastSocket, InetAddress address, User user) {
        this.multicastSocket = multicastSocket;
        this.address = address;
        this.buffer = new byte[1000];
        this.user = user;
    }

    @Override
    public void run() {
        while (true) {
            Message message = new Message();
            message.setText(this.user.getNickName()+ "joined the group");
            sendMessage(message);
        }
    }
    
    public void sendMessage(Message message){
        byte[] bytesMessage = message.toString().getBytes();
        DatagramPacket messageOut;
        try {
            messageOut = new DatagramPacket(bytesMessage, bytesMessage.length, this.address, multicastSocket.getLocalPort());
            multicastSocket.send(messageOut);
        } catch (SocketException ex) {
            System.err.println("Erro na comunicacao");
        } catch (IOException ex) {
            System.err.println("Erro ao enviar mensagem" + ex.getMessage());
        }
    }

}
