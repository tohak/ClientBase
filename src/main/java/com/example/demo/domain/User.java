package com.example.demo.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "usr_tbl")
public class User implements UserDetails {
    public static final boolean BUN_NULL = false;
    public static final int LENGTH = 100;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @NotBlank(message = "Login is required")
    @Column(name = "user_name", length = LENGTH, nullable = BUN_NULL, unique = true)
    private String username;

    @NotBlank(message = "Name is required")
    @Column(name = "name", length = LENGTH, nullable = BUN_NULL)
    private String name;

    @NotBlank(message = "Password cannot empty")
    @Column(name = "password", length = LENGTH, nullable = BUN_NULL)
    private String password;

    @NotBlank(message = "LastName is required")
    @Column(name = "user_last_name", length = LENGTH, nullable = BUN_NULL)
    private String userLastName;

    @NotBlank(message = "Patronymic is required")
    @Column(name = "user_patronymic", length = LENGTH, nullable = BUN_NULL)
    private String patronymic;

    @Column(name = "user_date")
    private LocalDate date;

    @Column(name = "user_family_status")
    private Boolean familyStatus;

    @Column(name = "user_inn")
    private Long inn;

    @Enumerated(EnumType.STRING)
    @Column(name = "education_enum")
    private Education state;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "status_id")
    private UserStatus userStatus;

    @ToString.Exclude
    @ManyToOne()
    @JoinColumn(name = "family_id")
    private Family family;

    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public boolean isAdmin() {
        return roles.contains(UserRole.ADMIN);
    }

    public boolean isUserAut() {
        return roles.contains(UserRole.USER);
    }

}
