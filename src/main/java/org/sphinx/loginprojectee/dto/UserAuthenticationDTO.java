package org.sphinx.loginprojectee.dto;

import lombok.*;
import org.sphinx.loginprojectee.model.Role;
import org.sphinx.loginprojectee.model.User;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAuthenticationDTO {
    private Long id;
    private Role role ;

    public static UserAuthenticationDTO fromUser(User user){
        return UserAuthenticationDTO.builder()
                .id(user.getId())
                .role(user.getRole())
                .build();
    }
}
