package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.User;
import com.untucapital.usuite.utg.service.AbstractService;
import com.untucapital.usuite.utg.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
