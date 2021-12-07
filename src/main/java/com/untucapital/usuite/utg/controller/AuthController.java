package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.controller.payload.*;
import com.untucapital.usuite.utg.service.UserService;
import com.untucapital.usuite.utg.utils.FormatterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

/**
 * @author Chirinda Nyasha Dell 22/11/2021
 */

@RestController
@RequestMapping("auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginReq loginReq) {

        Optional<LoginResp> loginRespOptional = userService.authenticateUser(loginReq);

        if (loginRespOptional.isPresent()) {
            return ResponseEntity.ok(loginRespOptional.get());
        } else return ResponseEntity.badRequest().body(new UsuiteApiResp("Invalid Credentials"));
    }

    @PostMapping("/signup")
    public ResponseEntity<UsuiteApiResp> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

        Optional<String> createdUserIdOptional = userService.registerUser(signUpRequest);

        if (createdUserIdOptional.isPresent()) {
            URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/users/{id}")
                    .buildAndExpand(createdUserIdOptional.get()).toUri();

            return ResponseEntity.created(location).body(new UsuiteApiResp("User registered successfully"));
        } else
            return ResponseEntity.badRequest().body(new UsuiteApiResp("Failed to register user"));
}

    @PostMapping("/confirm_account")
    public ResponseEntity<UsuiteApiResp> confirm(@RequestParam("username") String username,
                                                 @RequestParam("code") String code) {

        if (userService.confirmUserAccount(username, code)) {
            return ResponseEntity.ok(new UsuiteApiResp("User confirmed successfully"));
        } else
            return ResponseEntity.badRequest().body(new UsuiteApiResp("Failed to confirm user account"));
    }

    @GetMapping("/check_email")
    public ResponseEntity<UsuiteApiResp> checkEmail(@RequestParam("email") String email) {

        if (userService.checkUserEmail(email)) {
            return ResponseEntity.ok(new UsuiteApiResp("User email exists"));
        } else
            return ResponseEntity.badRequest().body(new UsuiteApiResp("Email provided does not exist"));
    }
}
