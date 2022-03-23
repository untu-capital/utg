package com.untucapital.usuite.utg.untuSite.repository;


import com.untucapital.usuite.utg.untuSite.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, String> {
    List<News> findNewsById (String id);
}
