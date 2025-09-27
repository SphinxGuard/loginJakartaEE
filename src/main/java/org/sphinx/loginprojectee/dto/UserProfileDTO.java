package org.sphinx.loginprojectee.dto;

import org.sphinx.loginprojectee.model.User;

public class UserProfileDTO {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    //private role

    public UserProfileDTO(String username, String email, String firstName, String lastName) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public static UserProfileDTO fromUser(User user) {
        return new UserProfileDTO(user.getUsername(), user.getEmail(), user.getFirstName(), user.getLastName());
    }
}

