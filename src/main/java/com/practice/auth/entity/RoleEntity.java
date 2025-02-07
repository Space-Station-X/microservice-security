package com.practice.auth.entity;

import com.practice.auth.Enums.EnumRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "roles")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private EnumRole enumRole;

    @ManyToMany(fetch = FetchType.EAGER , targetEntity = PermissionEntity.class)
    @JoinTable(name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<PermissionEntity> permissions = new HashSet<>();

    public RoleEntity(EnumRole enumRole) {
        this.enumRole = enumRole;
    }
}
