package models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString

public class User {

    private String name ;
    private String lastName ;
    private String email;
    private String password;

    public User(){} //подсвечен constructor default

    public User(String email, String password) {//custom constructor - 2й
        this.email = email;
        this.password = password;
    }
    public User(String email, String password, String name) {//custom constructor - 3й
//        this.email = email;
//        this.password = password;
        setEmail(email);
        setPassword(password);
        setName(name);
    }
    public User(String name, String lastName, String email, String password) {//custom constructor - 1й
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
    public void setName(String name) {
        if (name != null && name.isEmpty()) {
            this.name = name;
        }
    }
    public User withName(String name) {
        this.name = name;
        return this;
    }

    public void setLastName(String lastName) {
        if (lastName != null && lastName.isEmpty()) {
            this.lastName = lastName;
        }
    }
    public User withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setEmail(String email) {// @Setter - библиотека lombok
        if (email != null && email.isEmpty()) {
            this.email = email;
        }
    }
    public User withEmail(String email) {
        this.email = email;
        return this;
    }
    public void setPassword(String password) {// @Setter - библиотека lombok
        if (password != null && password.isEmpty()) {
            this.password = password;
        }
    }
    public User withPassword(String password) {
        this.password = password;
        return this;
    }
//    public String getName() {// @Getter - библиотека lombok
//        return name;
//    }
//    public String getLastName() {// @Getter - библиотека lombok
//        return lastName;
//    }
//    public String getEmail() {// @Getter - библиотека lombok
//        return email;
//    }
//    public String getPassword() {// @Getter - библиотека lombok
//        return password;
//    }
}
