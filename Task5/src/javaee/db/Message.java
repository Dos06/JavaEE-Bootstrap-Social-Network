package javaee.db;

import java.sql.Timestamp;

public class Message {
    int id;
    String messageText;
    boolean readByReceiver;
    Timestamp sentTime;
    Chat chat;
    User user, opponent;

    public Message() {
    }

    public Message(int id, String messageText, boolean readByReceiver, Timestamp sentTime, Chat chat, User user, User opponent) {
        this.id = id;
        this.messageText = messageText;
        this.readByReceiver = readByReceiver;
        this.sentTime = sentTime;
        this.chat = chat;
        this.user = user;
        this.opponent = opponent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public boolean isReadByReceiver() {
        return readByReceiver;
    }

    public void setReadByReceiver(boolean readByReceiver) {
        this.readByReceiver = readByReceiver;
    }

    public Timestamp getSentTime() {
        return sentTime;
    }

    public void setSentTime(Timestamp sentTime) {
        this.sentTime = sentTime;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getOpponent() {
        return opponent;
    }

    public void setOpponent(User opponent) {
        this.opponent = opponent;
    }
}
