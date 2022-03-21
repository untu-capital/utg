package com.untucapital.usuite.utg.service;


import com.untucapital.usuite.utg.model.Comments;
import com.untucapital.usuite.utg.model.DirectCost;
import com.untucapital.usuite.utg.repository.CommentsRepository;
import com.untucapital.usuite.utg.repository.DirectCostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class CommentsService {

    @Autowired
    private CommentsRepository commentsRepository;

    public void  saveComments(Comments comments) {
        commentsRepository.save(comments);
    }

    public List<Comments> getByLoanId(String loanId) {
        return commentsRepository.findbyLoanId(loanId);
    }
}
