package com.practice.auth.config;

import com.practice.auth.Enums.EnumPermission;
import com.practice.auth.Enums.EnumRole;
import com.practice.auth.entity.AuthEntity;
import com.practice.auth.entity.PermissionEntity;
import com.practice.auth.entity.RoleEntity;
import com.practice.auth.repository.AuthUserRepository;
import com.practice.auth.repository.PermissionRepository;
import com.practice.auth.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CommandInitializerConfig implements CommandLineRunner {

    private final PermissionRepository permissionRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final AuthUserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        // Inicializar permisos
        if (permissionRepository.count() == 0) {
            for (EnumPermission permission : EnumPermission.values()) {
                PermissionEntity newPermission = new PermissionEntity();
                newPermission.setName(permission.name());
                permissionRepository.save(newPermission);
            }
            System.out.println("Permissions initialized.");
        }

        // Inicializar roles
        if (roleRepository.count() == 0) {
            Set<PermissionEntity> allPermissions = new HashSet<>(permissionRepository.findAll());

            RoleEntity adminRole = new RoleEntity(EnumRole.ADMIN);
            adminRole.setPermissions(allPermissions);
            roleRepository.save(adminRole);

            RoleEntity userRole = new RoleEntity(EnumRole.USER);
            roleRepository.save(userRole);

            System.out.println("Roles initialized.");
        }

        if (userRepository.count() == 0) {
            RoleEntity adminRole = roleRepository.findByEnumRole(EnumRole.ADMIN)
                    .orElseThrow(() -> new RuntimeException("El rol ADMIN no existe"));
            RoleEntity userRole = roleRepository.findByEnumRole(EnumRole.USER)
                    .orElseThrow(() -> new RuntimeException("El rol USER no existe"));

            AuthEntity admin = AuthEntity.builder()
                    .email("admin@example.com")
                    .password(passwordEncoder.encode("admin123"))
                    .roles(Set.of(adminRole))
                    .build();

            AuthEntity user1 = AuthEntity.builder()
                    .email("user1@example.com")
                    .password(passwordEncoder.encode("user123"))
                    .roles(Set.of(userRole))
                    .build();

            AuthEntity user2 = AuthEntity.builder()
                    .email("user2@example.com")
                    .password(passwordEncoder.encode("password2"))
                    .roles(Set.of(userRole))
                    .build();
            AuthEntity user3 = AuthEntity.builder()
                    .email("rayd@gmail.com")
                    .password(passwordEncoder.encode("2005"))
                    .roles(Set.of(userRole))
                    .build();

            userRepository.save(admin);
            userRepository.save(user1);
            userRepository.save(user2);

            System.out.println("Initial users created.");
        }
    }
}
