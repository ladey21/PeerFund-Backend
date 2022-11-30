package com.example.funds.services;

import com.example.funds.dto.CommentDto;
import com.example.funds.entities.Comment;


import java.util.List;

public interface CommentService {

    void createComment(Long userId, Long groupId, CommentDto commentDto);

    List<Comment> allCommentsForAGroup();

}
