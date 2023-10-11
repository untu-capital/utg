package com.untucapital.usuite.utg.repository2;

import com.untucapital.usuite.utg.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<AccountEntity,Integer> {
}
