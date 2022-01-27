package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.ClientLoan;
import com.untucapital.usuite.utg.model.User;
import com.untucapital.usuite.utg.service.AbstractService;
import com.untucapital.usuite.utg.service.ClientLoanApplication;
import com.untucapital.usuite.utg.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Chirinda Nyasha Dell 22/11/2021
 */

@RestController
@RequestMapping(path = "users")
public class UsersController extends AbstractController<User> {

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


    @GetMapping("/role")
    public ResponseEntity<String> getUserByRole(@PathVariable("roles") String roles) {
        List<User> userRoles = userService.getUserByRole(roles);
        return ResponseEntity.ok(roles);
    }
}
