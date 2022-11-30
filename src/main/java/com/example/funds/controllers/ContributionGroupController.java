package com.example.funds.controllers;

import com.example.funds.dto.GroupDTO;
import com.example.funds.entities.ContributionGroup;
import com.example.funds.entities.Request;
import com.example.funds.entities.User;
import com.example.funds.services.ContributionGroupService;
import com.example.funds.services.RequestService;
import com.example.funds.services.UserService;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class ContributionGroupController {

    private final UserService userService;
    private final ContributionGroupService groupService;
    private final RequestService requestService;



    public ContributionGroupController(UserService userService, ContributionGroupService groupService, RequestService requestService) {
        this.userService = userService;
        this.groupService = groupService;
        this.requestService = requestService;
    }


    @PostMapping("{uid}/groups")
    public ResponseEntity<String> createGroup(@PathVariable(name = "uid")Long id,@RequestBody GroupDTO groupDTO){
        return ResponseEntity.ok(groupService.createGroup(id,groupDTO));
    }

    @GetMapping("{uid}/public-groups")
    public ResponseEntity<List<ContributionGroup>> getAllGroupsForAll(){
        return ResponseEntity.ok(groupService.allGroups());
    }

    @GetMapping("{uid}/public-groups/{gid}")
    public ResponseEntity<ContributionGroup> getAGroupsFromAllGroups(@PathVariable(name ="gid")Long idg){
        return ResponseEntity.ok(groupService.viewGroup(idg));
    }

    @GetMapping("{uid}/groups")
    public ResponseEntity<List<ContributionGroup>> getAllGroupsByUser(@PathVariable(name = "uid")Long id){
//        User user = userService.findByEmail(currentUser.getName());
        return ResponseEntity.ok(groupService.allGroupsCreatedByUser(id));
    }

    @GetMapping("{uid}/groups/{gid}")
    public ResponseEntity<ContributionGroup> viewGroup(@PathVariable(name = "gid")Long id){
        return ResponseEntity.ok(groupService.viewGroup(id));
    }

    @PatchMapping("{uid}/groups/{gid}")
    public ResponseEntity<String> updateGroup(@PathVariable(name = "uid")Long id,@RequestBody GroupDTO groupDTO, @PathVariable(name = "gid")Long idg){
        return ResponseEntity.ok(groupService.updateGroup(id,groupDTO,idg));
    }

    @DeleteMapping("{uid}/groups/{gid}")
    public ResponseEntity<String> deleteGroup(@PathVariable(name = "gid")Long idg){
        return ResponseEntity.ok(groupService.removeGroup(idg));
    }


    @GetMapping("{uid}/groups/{gid}/members")
    public ResponseEntity<List<User>> getAllMembersToGroup(@PathVariable(name = "gid")Long id){
        return ResponseEntity.ok(userService.membersOfGroup(id));
    }

    @GetMapping("{uid}/groups/{gid}/requests")
    public ResponseEntity<List<Request>> getAllRequestToGroups(@PathVariable(name = "gid")Long id){
        return ResponseEntity.ok(requestService.getAllRequestsToGroup(id));
    }

    @GetMapping("users/{uid}/requests")
    public ResponseEntity<List<Request>> allRequestsForUser(@PathVariable(name = "uid")Long id){
        return ResponseEntity.ok(requestService.AllRequestsMadeByUser(id));
    }

    @GetMapping("users/{uid}/requests/{rid}")
    public ResponseEntity<Request> getRequestDetails(@PathVariable(name = "rid") Long id){
        return ResponseEntity.ok(requestService.viewRequest(id));
    }

    @GetMapping("{uid}/public-groups/{gid}/requests/join")
    public ResponseEntity<String> createRequestToJoinGroup(@PathVariable(name = "uid")Long id,@PathVariable(name = "gid")Long idg){
        return ResponseEntity.ok(requestService.joinRequest(id,idg));
    }

    @GetMapping("{uid}/public-groups/{gid}/requests/exit")
    public ResponseEntity<String> createRequestToExitGroup(@PathVariable(name = "uid")Long id,@PathVariable(name = "gid")Long idg ){
        return ResponseEntity.ok(requestService.exitRequest(id, idg));
    }

    @GetMapping("{uid}/public-groups/{gid}/requests/{rid}/accept_join")
    public ResponseEntity<String> acceptRequestToJoinGroup(@PathVariable(name = "uid")Long id,@PathVariable(name = "gid")Long idg, @PathVariable(name = "rid")Long idr){
        return ResponseEntity.ok(requestService.approveJoinRequest(id,idr,idg));
    }

    @GetMapping("{uid}/groups/{gid}/requests/{rid}/accept_exit")
    public ResponseEntity<String> acceptRequestToExitGroup(@PathVariable(name = "uid")Long id,@PathVariable(name = "gid")Long idg, @PathVariable(name = "rid")Long idr){
        return ResponseEntity.ok(requestService.approveExitRequest(id,idr,idg));
    }

    @GetMapping("{uid}/groups/{gid}/requests/{rid}/decline_join")
    public ResponseEntity<String> declineRequestToJoinGroup(@PathVariable(name = "uid")Long id,@PathVariable(name = "gid")Long idg, @PathVariable(name = "rid")Long idr){
        return ResponseEntity.ok(requestService.declineRequest(id,idr,idg));
    }

    @PostMapping("{uid}/groups/{gid}/requests/{rid}/decline_exit")
    public ResponseEntity<String> declineRequestToExitGroup(@PathVariable(name = "uid")Long id,@PathVariable(name = "gid")Long idg, @PathVariable(name = "rid")Long idr){
        return ResponseEntity.ok(requestService.declineRequest(id,idr,idg));
    }


//    private Long getCurrentUserId(@AuthenticationPrincipal Principal currentUser){
//        User user = userService.findByEmail(currentUser.getName());
//        return user.getId();
//    }

}
