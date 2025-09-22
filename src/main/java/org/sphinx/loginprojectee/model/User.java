package org.sphinx.loginprojectee.model;
//hello
import jakarta.persistence.*;

@Entity
@Table(name = "MyUser") // or MyUser, or any non-reserved name
public class User {
    //Attributes
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;

    //Comstructors
    private User (){

    }

    public User (String firstName, String lastName, String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //getters and setters
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
