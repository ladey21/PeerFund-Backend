package com.example.funds.services.implementation;

import com.example.funds.dto.GroupDTO;
import com.example.funds.entities.ContributionGroup;
import com.example.funds.exceptions.PeerFundException;
import com.example.funds.repositories.ContributionGroupRepository;
import com.example.funds.repositories.UserRepository;
import com.example.funds.services.ContributionGroupService;
import com.example.funds.services.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContributionGroupImpl implements ContributionGroupService {

    private final ContributionGroupRepository groupRepository;
    private final UserRepository userRepository;

    private final UserService userService;

    public ContributionGroupImpl(ContributionGroupRepository groupRepository, UserRepository userRepository, UserService userService) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public String createGroup(Long userId, GroupDTO groupDTO) {
        boolean groupExist = groupRepository.existsContributionGroupByName(groupDTO.getName());
        if(groupExist){
            throw new PeerFundException(HttpStatus.BAD_REQUEST,"group exist already");
        } else{
            ContributionGroup group = new ContributionGroup();
            group.setName(groupDTO.getName());
            group.setPurpose_title(groupDTO.getPurpose());
            group.setSlots(groupDTO.getSlots());
            group.setDescription(groupDTO.getDescription());
            group.setGroup_amount(groupDTO.getAmount());
            ContributionGroup savedGroup = groupRepository.save(group);
            userService.userBecomeAdmin(userId, savedGroup.getId());
        }
        return "created";
    }

    @Override
    public List<ContributionGroup> allGroups() {
        return groupRepository.findAll();
    }

    @Override
    public ContributionGroup viewGroup(Long id) {
        return groupRepository.findContributionGroupById(id);
    }

    @Override
    public String updateGroup(Long userId, GroupDTO groupDTO, Long groupId) {
        ContributionGroup group = groupRepository.findContributionGroupByIdAndAdmin_Id(groupId,userId);
        group.setName(groupDTO.getName());
        group.setPurpose_title(group.getPurpose_title());
        group.setSlots(groupDTO.getSlots());
        group.setDescription(groupDTO.getDescription());
        group.setStartDate(groupDTO.getStartDate());
        group.setEndDate(groupDTO.getEndDate());
        group.setPaymentStartDate(groupDTO.getPaymentStartDate());
        group.setEndDate(groupDTO.getEndDate());
        group.setGroup_amount(groupDTO.getAmount());
        groupRepository.save(group);
        return "updated";
    }

    @Override
    public List<ContributionGroup> allGroupsCreatedByUser(Long id) {
        return groupRepository.findContributionGroupsByAdmin_Id(id);
    }


    @Override
    public String removeGroup(Long groupId) {
        groupRepository.deleteById(groupId);
        return "deleted";
    }

    private ContributionGroup findGroup(Long id){
        return groupRepository.findContributionGroupById(id);
    }

}
