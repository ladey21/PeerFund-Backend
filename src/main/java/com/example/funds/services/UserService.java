package com.example.funds.services;


import com.example.funds.dto.UserDto;
import com.example.funds.entities.User;

import java.util.List;

public interface UserService {

    String signUp(UserDto userDto);

    String updateProfile(Long userId,UserDto userDto);

    User getUser(Long id);

    List<User> membersOfGroup(Long groupId);

    User findByEmail(String email);

    void userBecomeAdmin(Long id,Long groupId);

}
