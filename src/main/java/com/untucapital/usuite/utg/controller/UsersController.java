package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.controller.payload.UsuiteApiResp;
import com.untucapital.usuite.utg.model.ClientLoan;
import com.untucapital.usuite.utg.model.Role;
import com.untucapital.usuite.utg.model.User;
import com.untucapital.usuite.utg.model.enums.RoleType;
import com.untucapital.usuite.utg.repository.ClientRepository;
import com.untucapital.usuite.utg.repository.RoleRepository;
import com.untucapital.usuite.utg.repository.UserRepository;
import com.untucapital.usuite.utg.service.AbstractService;
import com.untucapital.usuite.utg.service.ClientLoanApplication;
import com.untucapital.usuite.utg.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Chirinda Nyasha Dell 22/11/2021
 */

@RestController
@RequestMapping(path = "users")
public class UsersController extends AbstractController<User> {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    private static final Logger log = LoggerFactory.getLogger(UsersController.class);

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected AbstractService<User> getService() {
        return userService;
    }

    // Get list of all users with role of Loan Officer
    @GetMapping("/role/{roleName}")
    public ResponseEntity<List<User>> getUsersByRole(@PathVariable("roleName") String roleName) {
        return new ResponseEntity<List<User>>(userService.getUsersByRole(roleName), HttpStatus.OK);

    }

    //build get clientLoan by ID REST API
    @GetMapping("/test/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String userId) {
        return new ResponseEntity<User>(userRepository.getUserById(userId), HttpStatus.OK);
    }

    @PutMapping("/updateUserRole/{id}")
    public ResponseEntity<String> updateUserRole(@PathVariable String id, @RequestBody User user){
        User updatedUserRole = userRepository.getUserById(id);
        updatedUserRole.setRoles(user.getRoles());
        userRepository.save(updatedUserRole);
        return new ResponseEntity<String>("User role successfully updated.", HttpStatus.OK);
    }

}
