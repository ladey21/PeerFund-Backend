package com.example.funds.services;

import com.example.funds.dto.GroupDTO;
import com.example.funds.entities.ContributionGroup;


import java.util.List;


public interface ContributionGroupService {

    String createGroup(Long userId, GroupDTO groupDTO);

    List<ContributionGroup> allGroups();

    ContributionGroup viewGroup(Long id);

    String updateGroup(Long userId,GroupDTO groupDTO, Long groupId);

    List<ContributionGroup> allGroupsCreatedByUser(Long id);

    String removeGroup(Long groupId);
}
