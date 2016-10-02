/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chat;

import controller.User;

/**
 *
 * @author Leonardo Baiser <lpbaiser@gmail.com>
 */
public class Message {
    
    private String texto;
    private User sender;
    private User receiver;
    private TypeMessage typeMessage;

    public String getTexto() {
        return texto;
    }

    public void setText(String texto) {
        this.texto = texto;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public TypeMessage getTypeMessage() {
        return typeMessage;
    }

    public void setTypeMessage(TypeMessage typeMessage) {
        this.typeMessage = typeMessage;
    }


}
