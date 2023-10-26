package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.entity.PostGl;
import com.untucapital.usuite.utg.repository2.PostGlRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class PostGlService {

    private final PostGlRepository postGlRepository;

    @Transactional(value= "pastelTransactionManager")
    public PostGl savePostGl(PostGl postGl){
         return postGlRepository.save(postGl);
    }

    @Transactional(value= "pastelTransactionManager")
    public List<PostGl> getAllPostGl(){
        return postGlRepository.findAll();
    }

//    @Transactional(value= "pastelTransactionManager")
//    public List<PostGl> getAllPostGlByAccountLink(Integer accountLink){
//
//        return postGlRepository.findByAccountLink(accountLink);
//    }

//    @Transactional(value= "pastelTransactionManager")
//    public List<PostGl> getAllPostGlByTxDate(Date txDate){
//
//        return postGlRepository.findByTxDate(txDate);
//    }

}
