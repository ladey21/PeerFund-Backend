package com.example.funds.services;


import com.example.funds.entities.Request;


import java.util.List;

public interface RequestService {

    String joinRequest(Long userId, Long groupId);

    String exitRequest(Long userId, Long groupId);

    List<Request> getAllRequestsToGroup(Long groupId);

    List<Request> AllRequestsMadeByUser(Long userId);

    Request viewRequest(Long requestId);

    String approveJoinRequest(Long userId,Long requestId,Long groupId);

    String approveExitRequest(Long userId, Long requestId, Long groupId);

    String declineRequest(Long userId,Long requestId,Long groupId);
}
