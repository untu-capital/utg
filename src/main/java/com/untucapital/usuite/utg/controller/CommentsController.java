package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.Comments;
import com.untucapital.usuite.utg.model.DirectCost;
import com.untucapital.usuite.utg.service.CommentsService;
import com.untucapital.usuite.utg.service.DirectCostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentsController {
    @Autowired
    CommentsService commentsService;

    private static final Logger log = LoggerFactory.getLogger(CommentsController.class);

    @PostMapping("/addComments")
    public void add(@RequestBody Comments comments) {
        commentsService.saveComments(comments);
    }

    @GetMapping("/getComments/{loanId}")
    public List<Comments> getByLoanId(@PathVariable("loanId") String loanId) {
        return commentsService.getByLoanId(loanId);
    }
}

