package org.sphinx.loginprojectee.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "MyUser") // or MyUser, or any non-reserved name
public class User {
    //Attributes
    private Long id;
    @Getter
    @Setter
    private String firstName;
    @Getter
    @Setter
    private String lastName;
    @Setter
    private String username;
    @Getter
    @Setter
    private String password;
    @Setter
    private String email;
    @Setter
    private Role role;


    //Constructors
    private User (){

    }
    public User (String firstName, String lastName, String username, String password, String email , Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }


    @Column(unique = true)
    public String getEmail() {
        return email;
    }


    @Enumerated(EnumType.STRING)
    public Role getRole() {
        return role;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

}
