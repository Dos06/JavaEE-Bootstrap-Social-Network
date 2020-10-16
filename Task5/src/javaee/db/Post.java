package javaee.db;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Post {
    int id;
    String title, shortContent, content;
    Timestamp postDate;
    User user;

    public Post() {
    }

    public Post(int id, String title, String shortContent, String content, Timestamp postDate, User user) {
        this.id = id;
        this.title = title;
        this.shortContent = shortContent;
        this.content = content;
        this.postDate = postDate;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortContent() {
        return shortContent;
    }

    public void setShortContent(String shortContent) {
        this.shortContent = shortContent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getPostDate() {
        return postDate;
    }

//    public String getMonthName() throws ParseException {
//        Date d = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(postDate.getYear()+1900 + "/" + postDate.getMonth() + "/" + postDate.getDate());
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(d);
//        String monthName = new SimpleDateFormat("MMMM").format(cal.getTime());
//        return monthName;
//    }

    public void setPostDate(Timestamp postDate) {
        this.postDate = postDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", shortContent='" + shortContent + '\'' +
                ", content='" + content + '\'' +
                ", postDate=" + postDate +
                ", user=" + user +
                '}';
    }
}
