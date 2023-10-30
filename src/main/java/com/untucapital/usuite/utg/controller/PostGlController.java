package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.DTO.response.PostGLResponseDTO;
import com.untucapital.usuite.utg.entity.PostGl;
import com.untucapital.usuite.utg.model.transactions.TransactionInfo;
import com.untucapital.usuite.utg.service.PostGlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(value = "postGl/", produces = "application/json")
@RequiredArgsConstructor
public class PostGlController {

    private final PostGlService postGlService;

    @GetMapping("all")
    public ResponseEntity<List<PostGLResponseDTO>> getAllPostGl() {

        return ResponseEntity.ok(postGlService.getAllPostGl());
    }

    @PostMapping("save")
    public ResponseEntity<String> saveTransaction(@RequestBody TransactionInfo transactionInfo) {

        try {
            postGlService.savePostGlFromCMS(transactionInfo);
            return new ResponseEntity<>("Transaction saved successfully", HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("Failed to save transaction: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

//    @GetMapping("getByAccountLink")
//    public ResponseEntity<List<PostGLResponseDTO>> getByAccountLink(@PathVariable("AccountLink") Integer accountLink) {
//
//        return ResponseEntity.ok(postGlService.getAllPostGlByAccountLink(accountLink));
//    }
//
//    @GetMapping("getByTxDate")
//    public ResponseEntity<List<PostGLResponseDTO>> getByTxDate(@PathVariable("TxDate") Date date) {
//
//        return ResponseEntity.ok(postGlService.getAllPostGlByTxDate(date));
//    }
}
