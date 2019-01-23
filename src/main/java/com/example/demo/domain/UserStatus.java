package com.example.demo.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/*
 * Сущность статуса пользователя
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "usr_status_tbl")
public class UserStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "user_status", nullable = false)
    private String statusUser;

    @OneToMany(mappedBy = "userStatus", cascade = CascadeType.ALL)
    private Set<User> users;

}
