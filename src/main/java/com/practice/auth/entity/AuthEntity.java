package com.practice.auth.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
@Table(name = "auth")
@Entity
public class AuthEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //    @Column(name = "id_user")
//    private Long idUser;
    @Column(unique = true)
    @Email(
            message = "Ingresa correctamente el email",
            regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"
    )
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, targetEntity = RoleEntity.class)
    @JoinTable(name = "auth_roles",
            joinColumns = @JoinColumn(name = "auth_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();
}
