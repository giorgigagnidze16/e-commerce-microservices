package com.ecom.microservice.entity;

import java.util.Collection;
import java.util.List;

import com.google.common.base.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
@Getter
@Builder
@ToString
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 50)
    @Column(nullable = false)
    private String firstname;

    @Size(min = 2, max = 50)
    @Column(nullable = false)
    private String lastname;

    @Email
    @Size(min = 5)
    @Column(nullable = false)
    private String email;

    @Column
    @ToString.Exclude
    private String password;

    @OneToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    public User() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> role.getName());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equal(firstname, user.firstname) &&
            Objects.equal(lastname, user.lastname) &&
            Objects.equal(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(firstname, lastname, email);
    }
}
