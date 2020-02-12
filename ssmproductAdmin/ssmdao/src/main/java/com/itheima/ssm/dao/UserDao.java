package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao {

    @Select("select * from users")
    List<UserInfo> findAll();

    @Insert("insert into users (username,email,password,phoneNum,status) " +
            "values(#{username},#{email},#{password},#{phoneNum},#{status})")
    void  saveUser(UserInfo userInfo);

    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status" ),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.itheima.ssm.dao.RoleDao.findRoleByUserId"))
})
    UserInfo findUserInfoByUsername(String username);

    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status" ),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.itheima.ssm.dao.RoleDao.findRoleByUserId"))
    })
    UserInfo findUserById(String id);

    @Select("select * from role where id not in (select roleId from users_role where userId=#{userId})")
    List<Role> findOtherRoles(String userId);

    @Insert("insert into users_role (userId,roleId) values(#{userId},#{id})")
    void addRoleToUser(@Param("userId")String userId, @Param("id")String id);
}
