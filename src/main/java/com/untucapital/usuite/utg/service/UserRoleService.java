package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.Role;
import com.untucapital.usuite.utg.model.User;
import com.untucapital.usuite.utg.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class UserRoleService extends AbstractService<Role> {

    private static final Logger log = LoggerFactory.getLogger(UserRoleService.class);

    private final RoleRepository roleRepository;

    public UserRoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    protected JpaRepository<Role, String> getRepository() {
        return roleRepository;
    }

    @Override
    public List<User> getUserByRole(String name) {
        return null;
    }
}
