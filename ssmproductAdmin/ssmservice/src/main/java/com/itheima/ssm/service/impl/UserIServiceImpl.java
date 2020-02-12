package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.UserDao;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.UserService;
import com.itheima.ssm.utils.PasswordEncoderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserIServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<UserInfo> findAll() {
        return userDao.findAll();
    }

    @Override
    public void saveUser(UserInfo userInfo) {
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.saveUser(userInfo);
    }

    @Override
    public UserInfo findUserById(String id) {
        return userDao.findUserById(id);
    }

    @Override
    public List<Role> findOtherRoleToUser(String userId) {
        return userDao.findOtherRoles(userId);
    }

    @Override
    public void addRoleToUser(String userId, String[] ids) {
        for (String id:ids){
            userDao.addRoleToUser(userId,id);
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userDao.findUserInfoByUsername(username);
        User user = new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus()==0?false:true
        ,true,true,true,getAuthority(userInfo.getRoles()));
        return user;
    }

    public List<SimpleGrantedAuthority> getAuthority(List<Role> roleList){
        List<SimpleGrantedAuthority> authorityList = new ArrayList<SimpleGrantedAuthority>();
        for (Role role : roleList){
            authorityList.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return authorityList;
    }
}
