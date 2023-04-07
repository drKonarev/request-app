package ru.drKonarev.requestapp.user;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "Users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotNull
    private String name;
    @Column(name = "email", unique = true, nullable = false)
    @Email(message = "Wrong email format!")
    private String email;

   @ElementCollection(targetClass = Roles.class, fetch = FetchType.EAGER)
   @CollectionTable(name = "user_role",
   joinColumns = @JoinColumn(name = "user_id"))
   @Enumerated(EnumType.STRING)
   private Set<Roles> roles;

    @Column(name = "password", nullable = false, length = 100)
    private String password;


    // security
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

