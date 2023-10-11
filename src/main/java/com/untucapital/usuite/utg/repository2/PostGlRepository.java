package com.untucapital.usuite.utg.repository2;

import com.untucapital.usuite.utg.entity.PostGl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostGlRepository extends JpaRepository<PostGl, Long> {

}
