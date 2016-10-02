
import chat.Client;
import controller.User;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Leonardo Baiser <lpbaiser@gmail.com>
 */
public class ClientTest {

    private final Runnable listener = null;
    private final Runnable talker = null;

    @Test
    public void clientTest() {

        try {
            User user = new User();
            user.setNickName("LEO");
            user.setIp("255.1.2.3");
            user.setPort(6789);

            Client client = new Client();
            client.joinGroup(user);
        } catch (NumberFormatException ex) {
            throw new RuntimeException("listenTOPort talkToHost talkToPort");
        }
    }

}
