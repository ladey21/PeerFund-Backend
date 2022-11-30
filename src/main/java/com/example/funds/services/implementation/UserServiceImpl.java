package com.example.funds.services.implementation;


import com.example.funds.dto.UserDto;
import com.example.funds.entities.ContributionGroup;
import com.example.funds.entities.Enums.RoleType;
import com.example.funds.entities.Role;
import com.example.funds.entities.User;
import com.example.funds.exceptions.BadRequestException;
import com.example.funds.repositories.ContributionGroupRepository;
import com.example.funds.repositories.RoleRepository;
import com.example.funds.repositories.UserRepository;
import com.example.funds.services.UserService;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {



    private final ContributionGroupRepository groupRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;

    public UserServiceImpl( ContributionGroupRepository groupRepository, UserRepository userRepository, ModelMapper modelMapper, RoleRepository roleRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
    }


    @Override
    public String signUp(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmailAddress())) {
            throw new BadRequestException("Error: Email is already taken!");
        } else{
            User user = modelMapper.map(userDto,User.class);
            user.setPassword(userDto.getPassword());
//            Role role = roleRepository.findRoleByRoleType(RoleType.Member);
//            user.setRoles(Set.of(role));
            user.setRole(RoleType.Member);
            userRepository.save(user);
        }
        return "created";
    }

    @Override
    public String updateProfile(Long userId,UserDto userDto) {
        User user = getUser(userId);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        userRepository.save(user);
        return "updated";
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public List<User> membersOfGroup(Long groupId) {
        return groupRepository.findContributionGroupById(groupId).getMembers();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public void userBecomeAdmin(Long id,Long groupId) {
       ContributionGroup group = groupRepository.findContributionGroupById(groupId);
       User user = userRepository.findUserById(id);
//       Role role = roleRepository.findRoleByRoleType(RoleType.Admin);
//       user.getRoles().add(role);
        user.setRole(RoleType.Admin);
       userRepository.save(user);
       group.setAdmin(user);
       groupRepository.save(group);
    }

}
