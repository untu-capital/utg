package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.ContactDetail;
import com.untucapital.usuite.utg.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Chirinda Nyasha Dell 22/11/2021
 */

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username);

    boolean existsUserByUsername(String username);

    boolean existsByContactDetailMobileNumber(long mobileNumber);

    boolean existsByContactDetail_EmailAddress(String emailAddress);

    User findByContactDetail_EmailAddress(String email);

    //ContactDetail findByEmail(String email);

    User findByResetPasswordToken(String token);

    User getUserById (String userId);

    List<User> findUsersByBranch(String branchName);

    List<User> findUsersByCreditCommitGroup(String creditCommitGroupName);
}
