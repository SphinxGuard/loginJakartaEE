package org.sphinx.loginprojectee.dto;

import lombok.*;
import org.sphinx.loginprojectee.model.Role;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAuthenticationDTO {
    private Long id;
    private Role role ;
}
