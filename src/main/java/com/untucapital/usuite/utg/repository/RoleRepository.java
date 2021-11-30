package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.Role;
import com.untucapital.usuite.utg.model.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Chirinda Nyasha Dell 23/11/2021
 */


@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Optional<Role> findByName(RoleType name);
}
