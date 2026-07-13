package com.example.tp_integrador.entities;

import com.example.tp_integrador.enums.Rol;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true) @ToString
public class User extends Base implements UserDetails {

    private String firstName;
    private String lastName;
    @EqualsAndHashCode.Include
    private String email; // IDENTITY

    private String phone;
    private String password;

    @Enumerated(EnumType.STRING)
    private Rol role;

    @OneToMany(mappedBy = "user")
    private Set<Order> orders;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
