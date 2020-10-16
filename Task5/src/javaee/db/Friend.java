package javaee.db;

import java.sql.Timestamp;

public class Friend {
    int id;
    Timestamp addedTime;
    User user, friend;

    public Friend() {
    }

    public Friend(int id, Timestamp addedTime, User user, User friend) {
        this.id = id;
        this.addedTime = addedTime;
        this.user = user;
        this.friend = friend;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(Timestamp addedTime) {
        this.addedTime = addedTime;
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
