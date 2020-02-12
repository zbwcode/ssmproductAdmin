package com.itheima.ssm.service;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAllRoles();

    void saveRole(Role role);

   Role findRoleById(String roleId);

    List<Permission> findOtherPermissionByRoleId(String roleId);

    void addPermissionToRole(String roleId, String[] ids);
}
