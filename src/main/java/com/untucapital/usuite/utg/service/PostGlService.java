package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.PostGLRequestDTO;
import com.untucapital.usuite.utg.dto.response.PostGLResponseDTO;
import com.untucapital.usuite.utg.entity.AccountEntity;
import com.untucapital.usuite.utg.entity.PostGl;
import com.untucapital.usuite.utg.model.transactions.TransactionInfo;
import com.untucapital.usuite.utg.processor.PostGlProcessor;
import com.untucapital.usuite.utg.repository2.PostGlRepository;
import com.untucapital.usuite.utg.service.cms.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PostGlService {

    private final PostGlRepository postGlRepository;
    private final PostGlProcessor postGlProcessor;

    private final AccountService accountService;

    @Transactional(value= "pastelTransactionManager")
    public PostGLResponseDTO savePostGl(PostGLRequestDTO request){

         PostGl postGl = new PostGl();
        PostGLResponseDTO response = new PostGLResponseDTO();

        BeanUtils.copyProperties(request, postGl);
        postGlRepository.save(postGl);
        BeanUtils.copyProperties(postGl, response);
        return response;
    }

    @Transactional(value= "pastelTransactionManager")
    public void savePostGlFromCMS(TransactionInfo request)  {

        PostGl request1 = postGlProcessor.createFromAccountRequest(request);
        PostGl request2 = postGlProcessor.createToAccountRequest(request);

        postGlRepository.save(request1);
        postGlRepository.save(request2);

    }

    @Transactional(value= "pastelTransactionManager")
    public List<PostGLResponseDTO> getAllPostGl(){

        List<PostGLResponseDTO> response = new ArrayList<>();
        List<PostGl> postGlList= postGlRepository.findAll();

        for(PostGl postGl1: postGlList){
            PostGLResponseDTO postGl = new PostGLResponseDTO();
            BeanUtils.copyProperties(postGl1, postGl);
            response.add(postGl);
        }

        return response;
    }

    @Transactional(value= "pastelTransactionManager")
    public List<PostGLResponseDTO> getAllPostGlByAccountLink(Integer accountLink){

        List<PostGLResponseDTO> response = new ArrayList<>();
        List<PostGl> postGlList= postGlRepository.findByAccountLink(accountLink);
        for(PostGl postGl : postGlList) {

            PostGLResponseDTO postGLResponseDTO = new PostGLResponseDTO();
            BeanUtils.copyProperties(postGl, postGLResponseDTO);

            response.add(postGLResponseDTO);
        }

        return response;

    }

    @Transactional(value= "pastelTransactionManager")
    public List<PostGLResponseDTO> getAllPostGlByTxDate(Date txDate){

        List<PostGLResponseDTO> response = new ArrayList<>();
        List<PostGl> postGlList = postGlRepository.findByTxDate(txDate);
        for(PostGl postGl : postGlList) {

            PostGLResponseDTO postGLResponseDTO = new PostGLResponseDTO();
            BeanUtils.copyProperties(postGl, postGLResponseDTO);

            response.add(postGLResponseDTO);
        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public Float getVaultAccountBalance(String account){

        AccountEntity accountEntity = accountService.findAccountByAccount(account);
        Integer accountLink = accountEntity.getAccountLink();

        log.info("AccountLink:{}",accountLink);
        Float postGlBalances = postGlRepository.findAccountBalanceByAccountLink(accountLink);

        log.info("PostGlBalances:{}",postGlBalances);


        return postGlBalances;
    }
}
