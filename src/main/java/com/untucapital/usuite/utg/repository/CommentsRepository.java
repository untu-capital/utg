package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, String> {

    @Modifying
    @Query(value = "select * from comments where loan_id = :loanId", nativeQuery = true)
    List<Comments> findbyLoanId(String loanId);
}
