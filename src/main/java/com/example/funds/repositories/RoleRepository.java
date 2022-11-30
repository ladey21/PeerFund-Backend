package com.example.funds.repositories;

import com.example.funds.entities.Enums.RoleType;
import com.example.funds.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findRoleByRoleType(RoleType roleType);

}
