package org.sphinx.loginprojectee.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.sphinx.loginprojectee.model.User;

@Getter
@Setter
@Builder
@ToString
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


    public static UserProfileDTO fromUser(User user) {
        return UserProfileDTO.builder().username(user.getUsername()).email(user.getEmail())
                .firstName(user.getFirstName()).lastName(user.getLastName()).build();
    }
}

