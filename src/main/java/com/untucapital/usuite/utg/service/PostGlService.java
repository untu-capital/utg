package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.DTO.request.PostGLRequestDTO;
import com.untucapital.usuite.utg.DTO.response.PostGLResponseDTO;
import com.untucapital.usuite.utg.entity.AccountEntity;
import com.untucapital.usuite.utg.entity.PostGl;
import com.untucapital.usuite.utg.model.User;
import com.untucapital.usuite.utg.model.cms.Vault;
import com.untucapital.usuite.utg.model.transactions.TransactionInfo;
import com.untucapital.usuite.utg.processor.PostGlProcessor;
import com.untucapital.usuite.utg.repository.cms.VaultRepository;
import com.untucapital.usuite.utg.repository2.PostGlRepository;
import com.untucapital.usuite.utg.service.cms.AccountService;
import com.untucapital.usuite.utg.service.cms.VaultService;
import com.untucapital.usuite.utg.utils.EmailSender;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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

        Float currentBalanceFromAccount = getVaultAccountBalance(request.getFromAccount());

        Float currentBalanceToAccount = getVaultAccountBalance(request.getToAccount());

        PostGl transaction1 = postGlProcessor.createFromAccountRequest(request);
        PostGl transaction2 = postGlProcessor.createToAccountRequest(request);

        postGlProcessor.checkLimits(request, currentBalanceFromAccount, currentBalanceToAccount, user);

        postGlRepository.save(transaction1);
        postGlRepository.save(transaction2);

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

        Float postGlBalances = postGlRepository.findAccountBalanceByAccountLink(accountLink);

        return postGlBalances;
    }


}
