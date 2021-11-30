package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.auth.TokenProvider;
import com.untucapital.usuite.utg.auth.UserPrincipal;
import com.untucapital.usuite.utg.controller.payload.LoginReq;
import com.untucapital.usuite.utg.controller.payload.LoginResp;
import com.untucapital.usuite.utg.controller.payload.SignUpRequest;
import com.untucapital.usuite.utg.exception.ResourceNotFoundException;
import com.untucapital.usuite.utg.exception.UntuSuiteException;
import com.untucapital.usuite.utg.model.ConfirmationToken;
import com.untucapital.usuite.utg.model.ContactDetail;
import com.untucapital.usuite.utg.model.Role;
import com.untucapital.usuite.utg.model.User;
import com.untucapital.usuite.utg.model.enums.RoleType;
import com.untucapital.usuite.utg.repository.ConfirmationTokenRepository;
import com.untucapital.usuite.utg.repository.RoleRepository;
import com.untucapital.usuite.utg.repository.UserRepository;
import com.untucapital.usuite.utg.utils.EmailSender;
import com.untucapital.usuite.utg.utils.EmailValidator;
import com.untucapital.usuite.utg.utils.FormatterUtil;
import com.untucapital.usuite.utg.utils.RandomNumUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

/**
 * @author Chirinda Nyasha Dell 22/11/2021
 */

@Transactional
@Service
public class UserService extends AbstractService<User> {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final EmailValidator emailValidator;
    private final RoleRepository roleRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailSender emailSender;

    @Autowired
    public UserService(AuthenticationManager authenticationManager, TokenProvider tokenProvider,
                       UserRepository userRepository, EmailValidator emailValidator, RoleRepository roleRepository,
                       ConfirmationTokenRepository confirmationTokenRepository, BCryptPasswordEncoder passwordEncoder,
                       EmailSender emailSender) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userRepository = userRepository;
        this.emailValidator = emailValidator;
        this.roleRepository = roleRepository;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailSender = emailSender;
    }

    @Override
    protected JpaRepository<User, String> getRepository() {
        return userRepository;
    }

    public Optional<LoginResp> authenticateUser(LoginReq loginReq) {
        log.debug("User Authentication Request - {}", FormatterUtil.toJson(loginReq));

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword()));

        Optional<LoginResp> loginRespOptional = Optional.empty();
        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String accessToken = tokenProvider.generateToken(authentication);
            loginRespOptional = Optional.of(
                    new LoginResp(accessToken, ((UserPrincipal) authentication.getPrincipal()).getId())
            );
        }
        return loginRespOptional;
    }

    public Optional<String> registerUser(SignUpRequest signUpRequest) {
        log.debug("User Registration Request - {}", FormatterUtil.toJson(signUpRequest));

        if (userRepository.existsUserByUsername(signUpRequest.getUsername())) {
            throw new ValidationException("Username is already taken");
        }

        if (userRepository.existsByContactDetailMobileNumber(signUpRequest.getMobileNumber())) {
            throw new ValidationException("Mobile Number already exists");
        }

        if (!emailValidator.test(signUpRequest.getEmail())) {
            throw new ValidationException("Email Address not valid");
        }

        if (userRepository.existsByContactDetail_EmailAddress(signUpRequest.getEmail())) {
            throw new ValidationException("Email Address already in exists");
        }

        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setActive(false);
        user.setVerified(false);

        ContactDetail cd = new ContactDetail();
        cd.setEmailAddress(signUpRequest.getEmail());
        cd.setMobileNumber(signUpRequest.getMobileNumber());
        user.setContactDetail(cd);

        Role userRole = roleRepository.findByName(RoleType.ROLE_CLIENT)
                .orElseThrow(() -> new UntuSuiteException("User Role not set."));
        user.setRoles(Collections.singleton(userRole));

        log.info("Registering new user - {}", FormatterUtil.toJson(signUpRequest));

        User createdUser = userRepository.save(user);

        // Generate and Save confirmation token
        String token = RandomNumUtils.generateCode(10);

        ConfirmationToken confirmToken = new ConfirmationToken();
        confirmToken.setToken(token);
        confirmToken.setExpirationDate(LocalDateTime.now().plusMinutes(30));
        confirmToken.setUser(createdUser);
        confirmationTokenRepository.save(confirmToken);

        String emailText = buildConfirmationEmail(user.getFirstName(), user.getUsername(), token);
        // emailSender.send(user.getContactDetail().getEmailAddress(), "Untu Credit Application Account Verification", emailText);
        // emailSender.sendMail(user.getContactDetail().getEmailAddress(), "Untu Credit Application Account Verification", emailText);

        return Optional.of(createdUser.getId());
    }

    private String buildConfirmationEmail(String firstName, String username, String token) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Dear " + firstName + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering on Untu Capital Credit Application. Please click on the below link to activate your account: " +
                "               </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"http://localhost:8080/auth/account-confirm?username=" + username + "&code=" + token + "\">Confirm Account</a> </p></blockquote>\n Link will expire in 30 minutes. <p>Cheers</p>\n<p>Untu Credit Application Team</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }

    private String createRegEmailText(String firstName, String lastName, String username, String code) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n Dear ").append(firstName).append(" ").append(lastName)
                .append(", \n\n Follow the link below to verify your email address \n http://localhost:4200/authentication/account-confirm?username=")
                .append(username)
                .append("&code=").append(code)
                .append("\n\n\n Regards \n Untu Credit Application Team.");
        return sb.toString();
    }

    public boolean confirmUserAccount(String username, String verificationCode) {
        log.debug("User Confirmation Request username: {}, verificationCode: {}", username, verificationCode);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Username", username));

        ConfirmationToken confirmationToken = confirmationTokenRepository.findConfirmationTokenByToken(verificationCode)
                .orElseThrow(() -> new ResourceNotFoundException("ConfirmationToken", "verification code", verificationCode));

        if (!confirmationToken.getToken().equals(verificationCode)) {
            throw new ValidationException("Verification Failed. Invalid verification code");
        }

        if (user.isVerified() || confirmationToken.getDateConfirmed() != null) {
            throw new ValidationException("Verification code already confirmed.");
        }

        if (confirmationToken.getExpirationDate().isBefore(LocalDateTime.now())) {
            throw new ValidationException("Verification Code already expired.");
        }

        log.info("Confirming new User - {}", FormatterUtil.toJson(user));
        user.setVerified(true);
        user.setActive(true);
        userRepository.save(user);

        confirmationToken.setDateConfirmed(LocalDateTime.now());
        confirmationTokenRepository.save(confirmationToken);
        return true;
    }
}
