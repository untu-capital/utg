package com.untucapital.usuite.utg.repository2;

import com.untucapital.usuite.utg.entity.PostGl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Repository
public interface PostGlRepository extends JpaRepository<PostGl, BigInteger> {
    List<PostGl> findByTxDate(Date txDate);

    List<PostGl> findByAccountLink(Integer AccountLink);

    @Query("SELECT SUM(p.Debit) - SUM(p.Credit) " +
            "FROM PostGl p " +
            "WHERE p.accountLink = :accountLink")
    Float findAccountBalanceByAccountLink(@Param("accountLink") Integer accountLink);

}
