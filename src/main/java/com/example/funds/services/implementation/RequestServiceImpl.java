package com.example.funds.services.implementation;

import com.example.funds.entities.ContributionGroup;
import com.example.funds.entities.Enums.RequestStatus;
import com.example.funds.entities.Request;
import com.example.funds.entities.User;
import com.example.funds.exceptions.PeerFundException;
import com.example.funds.repositories.ContributionGroupRepository;
import com.example.funds.repositories.RequestRepository;
import com.example.funds.repositories.UserRepository;
import com.example.funds.services.RequestService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    private final UserRepository userRepository;
    private final RequestRepository requestRepository;
    private final ContributionGroupRepository groupRepository;

    public RequestServiceImpl(UserRepository userRepository, RequestRepository requestRepository, ContributionGroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.requestRepository = requestRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public String joinRequest(Long userId, Long groupId) {
        ContributionGroup group = groupRepository.findContributionGroupById(groupId);
        User user = userRepository.findUserById(userId);
        boolean requestExist = group.getRequests().stream().anyMatch(request -> request.getMember().getId().equals(userId));
        if(requestExist){
            throw new PeerFundException(HttpStatus.BAD_REQUEST,"request created already");
        } else{
            Request request = new Request(user, LocalDateTime.now(),group, RequestStatus.APPLIED);
            requestRepository.save(request);
        }
        return "join request created";
    }

    @Override
    public String exitRequest(Long userId, Long groupId) {
        ContributionGroup group = groupRepository.findContributionGroupById(groupId);
        User user = userRepository.findUserById(userId);
        boolean groupNotActive = group.getStartDate().isBefore(LocalDateTime.now().plusDays(1));
        if(groupNotActive){
            throw new PeerFundException(HttpStatus.BAD_REQUEST,"cannot exit group cos it starts in a day time");
        } else{
            Request request = new Request(user,LocalDateTime.now(),group,RequestStatus.APPLIED);
        }
        return "exit request created";
    }

    @Override
    public List<Request> getAllRequestsToGroup(Long groupId) {
        return findGroup(groupId).getRequests();
    }

    @Override
    public List<Request> AllRequestsMadeByUser(Long userId) {
        return userRepository.findUserById(userId).getRequests();
    }

    @Override
    public Request viewRequest(Long requestId) {
        return requestRepository.findRequestById(requestId);
    }

    @Override
    public String approveJoinRequest(Long userId, Long requestId, Long groupId) {
        requestApproval(requestId,groupId);
        joinGroup(groupId,userId);
        return "approved";
    }


    @Override
    public String approveExitRequest(Long userId, Long requestId, Long groupId) {
        requestApproval(requestId,groupId);
        exitGroup(groupId,userId);
        return "approved";
    }

    @Override
    public String declineRequest(Long userId, Long requestId, Long groupId) {
        Request request = requestRepository.findRequestByIdAndContributionGroup_Id(requestId,groupId);
        request.setRequestStatus(RequestStatus.DECLINED);
        requestRepository.save(request);
        return "declined";
    }

    private void requestApproval(Long rid,Long gid){
        Request request = requestRepository.findRequestByIdAndContributionGroup_Id(rid,gid);
        request.setRequestStatus(RequestStatus.APPROVED);
        requestRepository.save(request);
    }

    private void joinGroup(Long groupId, Long userId) {
        ContributionGroup group = findGroup(groupId);
        group.getMembers().add(userRepository.findUserById(userId));
        groupRepository.save(group);
    }

    private void exitGroup(Long groupId, Long userId) {
        ContributionGroup group = findGroup(groupId);
        group.getMembers().remove(userRepository.findUserById(userId));
        groupRepository.save(group);
    }

    private ContributionGroup findGroup(Long id){
        return groupRepository.findContributionGroupById(id);
    }
}
