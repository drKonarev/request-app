package ru.drKonarev.requestapp.user.dto;

import lombok.*;
import ru.drKonarev.requestapp.markers.Create;
import ru.drKonarev.requestapp.markers.Update;
import ru.drKonarev.requestapp.user.Roles;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class UserDto {
    private long id;

    @NotNull(groups = {Create.class})
    @NotBlank(groups = {Create.class})
    private String name;

    @Email(groups = {Create.class, Update.class})
    @NotNull(groups = {Create.class})
    private String email;
    @NotNull(groups = {Create.class})
    @NotBlank(groups = {Create.class})
    private String password;

    @NotNull(groups = {Create.class, Update.class})
    @NotBlank(groups = {Create.class, Update.class})
    private Set<Roles> roles;
}
