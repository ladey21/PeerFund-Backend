package com.example.funds.services.implementation;

import com.example.funds.dto.CommentDto;
import com.example.funds.entities.Comment;
import com.example.funds.entities.ContributionGroup;
import com.example.funds.entities.User;
import com.example.funds.exceptions.PeerFundException;
import com.example.funds.repositories.CommentRepository;
import com.example.funds.repositories.ContributionGroupRepository;
import com.example.funds.repositories.UserRepository;
import com.example.funds.services.CommentService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final ContributionGroupRepository groupRepository;

    private final UserRepository userRepository;

    private final CommentRepository commentRepository;

    public CommentServiceImpl(ContributionGroupRepository groupRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public void createComment(Long userId, Long groupId, CommentDto commentDto) {
        User user = findUser(userId);
        ContributionGroup group = findGroup(groupId);
        boolean commentExist = group.getComments().stream().anyMatch(comment -> comment.getMember().getId().equals(userId));
        if(commentExist){
            throw new PeerFundException(HttpStatus.BAD_REQUEST,"comment exist already");
        } else{
            Comment comment = new Comment();
            comment.setMessage(commentDto.getMessage());
            comment.setMember(user);
            comment.setDate(LocalDateTime.now());
            comment.setContributionGroup(group);
            commentRepository.save(comment);
        }
    }

    @Override
    public List<Comment> allCommentsForAGroup() {
         List<Comment> comments = commentRepository.findAll();
         comments.sort(Comparator.comparing(comment -> comment.getDate().getMinute()));
         return comments;
    }

    private User findUser(Long id){
        return userRepository.findUserById(id);
    }

    private ContributionGroup findGroup(Long id){
        return groupRepository.findContributionGroupById(id);
    }
}
