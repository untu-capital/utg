package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.Sms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SMSRepo extends JpaRepository<Sms, String> {
}
