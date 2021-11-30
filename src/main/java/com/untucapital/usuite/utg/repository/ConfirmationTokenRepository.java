package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Chirinda Nyasha Dell 23/11/2021
 */

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, String> {


    Optional<ConfirmationToken> findConfirmationTokenByToken(String token);
}
