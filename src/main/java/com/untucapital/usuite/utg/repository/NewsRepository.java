package com.untucapital.usuite.utg.repository;


import com.untucapital.usuite.utg.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, String> {
    List<News> findNewsById (String id);
}
