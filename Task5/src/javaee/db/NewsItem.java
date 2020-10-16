package javaee.db;

import java.sql.Timestamp;

public class NewsItem {
    int id;
    String title, shortContent, content, pictureURL;
    Timestamp postDate;
    Language language;
    Publication publication;

    public NewsItem() {
    }

    public NewsItem(int id, String title, String shortContent, String content, String pictureURL, Timestamp postDate, Language language, Publication publication) {
        this.id = id;
        this.title = title;
        this.shortContent = shortContent;
        this.content = content;
        this.pictureURL = pictureURL;
        this.postDate = postDate;
        this.language = language;
        this.publication = publication;
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

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public Timestamp getPostDate() {
        return postDate;
    }

    public void setPostDate(Timestamp postDate) {
        this.postDate = postDate;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    @Override
    public String toString() {
        return "NewsItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", shortContent='" + shortContent + '\'' +
                ", content='" + content + '\'' +
                ", pictureURL='" + pictureURL + '\'' +
                ", postDate=" + postDate +
                ", language=" + language +
                ", publication=" + publication +
                '}';
    }
}
