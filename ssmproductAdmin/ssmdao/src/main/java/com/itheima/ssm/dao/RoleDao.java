package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleDao {
    @Select("select * from role where id in (select roleId from users_role where userId=#{id})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class
                    ,many=@Many(select = "com.itheima.ssm.dao.PermissionDao.findByRoleId") )
    })
    List<Role> findRoleByUserId(String id);


    @Select("select * from role")
    List<Role> findAll();

    @Insert("insert into role (roleName,roleDesc) values(#{roleName},#{roleName})")
    void saveRole(Role role);

    @Select("select * from role where id=#{roleId}")
    Role findRoleByRoleId(String roleId);

    @Select("select * from permission where id not in (select permissionId from role_permission where roleId = #{roleId})")
    List<Permission> findOtherPermissionByRoleId(String roleId);

    @Insert("insert into role_permission (roleId,permissionId) values (#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") String roleId,@Param("permissionId") String permissionId);
}
