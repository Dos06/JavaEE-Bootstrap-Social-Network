package javaee.db;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class User {
    int id;
    String email, password, fullName, pictureURL;
    Date birthdate;

    public User() {
    }

    public User(int id, String email, String password, String fullName, String pictureURL, Date birthdate) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.pictureURL = pictureURL;
        this.birthdate = birthdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public java.sql.Date getSqlBirthdate() {
        return new java.sql.Date(birthdate.getTime());
    }

    public int getAge() {
        LocalDate birthDate = getSqlBirthdate().toLocalDate();
        LocalDate currentDate =  LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", pictureURL='" + pictureURL + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
}
