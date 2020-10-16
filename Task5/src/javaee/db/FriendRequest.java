package javaee.db;

import java.sql.Timestamp;

public class FriendRequest {
    int id;
    Timestamp sentTime;
    User user, friend;

    public FriendRequest() {
    }

    public FriendRequest(int id, Timestamp sentTime, User user, User friend) {
        this.id = id;
        this.sentTime = sentTime;
        this.user = user;
        this.friend = friend;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getSentTime() {
        return sentTime;
    }

    public void setSentTime(Timestamp sentTime) {
        this.sentTime = sentTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }
}
