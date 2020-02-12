package com.itheima.ssm.service;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService{
    List<UserInfo> findAll();

    void saveUser(UserInfo userInfo);

    UserInfo findUserById(String id);


    List<Role> findOtherRoleToUser(String userId);

    void addRoleToUser(String userId, String[] ids);
}
