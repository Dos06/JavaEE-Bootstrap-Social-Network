package javaee.db;

import java.sql.Timestamp;

public class Chat {
    int id;
    Timestamp createdDate;
    String latestMessageText;
    Timestamp latestMessageTime;
    User user, opponent;

    public Chat() {
    }

    public Chat(int id, Timestamp createdDate, String latestMessageText, Timestamp latestMessageTime, User user, User opponent) {
        this.id = id;
        this.createdDate = createdDate;
        this.latestMessageText = latestMessageText;
        this.latestMessageTime = latestMessageTime;
        this.user = user;
        this.opponent = opponent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getLatestMessageText() {
        return latestMessageText;
    }

    public void setLatestMessageText(String latestMessageText) {
        this.latestMessageText = latestMessageText;
    }

    public Timestamp getLatestMessageTime() {
        return latestMessageTime;
    }

    public void setLatestMessageTime(Timestamp latestMessageTime) {
        this.latestMessageTime = latestMessageTime;
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

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", latestMessageText='" + latestMessageText + '\'' +
                ", latestMessageTime=" + latestMessageTime +
                ", user=" + user +
                ", opponent=" + opponent +
                '}';
    }
}
