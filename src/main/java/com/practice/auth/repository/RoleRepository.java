package com.practice.auth.repository;

import com.practice.auth.Enums.EnumRole;
import com.practice.auth.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    List<RoleEntity> findRoleEntitiesByEnumRoleIn(List<String> roles);
    Optional<RoleEntity> findByEnumRole(EnumRole role);
}
