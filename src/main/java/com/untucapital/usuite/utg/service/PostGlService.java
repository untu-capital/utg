package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.PostGLRequestDTO;
import com.untucapital.usuite.utg.dto.response.PostGLResponseDTO;
import com.untucapital.usuite.utg.entity.PostGl;
import com.untucapital.usuite.utg.entity.res.AccountEntityResponseDTO;
import com.untucapital.usuite.utg.entity.res.PostGlResponseDTO;
import com.untucapital.usuite.utg.model.User;
import com.untucapital.usuite.utg.model.transactions.TransactionInfo;
import com.untucapital.usuite.utg.processor.PostGlProcessor;
import com.untucapital.usuite.utg.repository.cms.VaultRepository;
import com.untucapital.usuite.utg.repository2.PostGlRepository;
import com.untucapital.usuite.utg.service.cms.AccountService;
import com.untucapital.usuite.utg.service.cms.VaultService;
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
    private final VaultRepository vaultRepository;
    private final VaultService vaultService;
    private final UserService userService;

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

        List<User> user = userService.findAll();
        PostGl trans1 = new PostGl();
        PostGl trans2 = new PostGl();

        Float currentBalanceFromAccount = getVaultAccountBalance(request.getFromAccount());

        Float currentBalanceToAccount = getVaultAccountBalance(request.getToAccount());

        PostGlResponseDTO transaction1 = postGlProcessor.createFromAccountRequest(request);
        BeanUtils.copyProperties(transaction1, trans1);
        PostGlResponseDTO transaction2 = postGlProcessor.createToAccountRequest(request);
        BeanUtils.copyProperties(transaction2, trans2);

        postGlProcessor.checkLimits(request, currentBalanceFromAccount, currentBalanceToAccount, user);

        postGlRepository.save(trans1);
        postGlRepository.save(trans2);

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
        List<PostGlResponseDTO> postGlList= postGlRepository.findByAccountLink(accountLink);
        for(PostGlResponseDTO postGl : postGlList) {

            PostGLResponseDTO postGLResponseDTO = new PostGLResponseDTO();
            BeanUtils.copyProperties(postGl, postGLResponseDTO);

            response.add(postGLResponseDTO);
        }

        return response;

    }

    @Transactional(value= "pastelTransactionManager")
    public List<PostGLResponseDTO> getAllPostGlByTxDate(Date txDate){

        List<PostGLResponseDTO> response = new ArrayList<>();
        List<PostGlResponseDTO> postGlList = postGlRepository.findByTxDate(txDate);
        for(PostGlResponseDTO postGl : postGlList) {

            PostGLResponseDTO postGLResponseDTO = new PostGLResponseDTO();
            BeanUtils.copyProperties(postGl, postGLResponseDTO);

            response.add(postGLResponseDTO);
        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public Float getVaultAccountBalance(String account){

        AccountEntityResponseDTO accountEntity = accountService.findAccountByAccount(account);
        Integer accountLink = accountEntity.getAccountLink();

        Float postGlBalances = postGlRepository.findAccountBalanceByAccountLink(accountLink);

        return postGlBalances;
    }


}
