package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.entity.PostGl;
import com.untucapital.usuite.utg.service.PostGlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(value = "postGl", produces = "application/json")
@RequiredArgsConstructor
public class PostGlController {

    private final PostGlService postGlService;

    @GetMapping("/all")
    public ResponseEntity<List<PostGl>> getAllPostGl() {

        return ResponseEntity.ok(postGlService.getAllPostGl());
    }

//    @GetMapping("/getByAccountLink")
//    public ResponseEntity<List<PostGl>> getByAccountLink(@PathVariable("accountLink") Integer accountLink) {
//
//        return ResponseEntity.ok(postGlService.getAllPostGlByAccountLink(accountLink));
//    }

//    @GetMapping("/getByTxDate")
//    public ResponseEntity<List<PostGl>> getByTxDate(@PathVariable("txDate") Date date) {
//
//        return ResponseEntity.ok(postGlService.getAllPostGlByTxDate(date));
//    }
}
