package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.RoleDao;
import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Override
    public List<Role> findAllRoles() {
        return roleDao.findAll();
    }

    @Override
    public void saveRole(Role role) {
        roleDao.saveRole(role);
    }

    @Override
    public Role findRoleById(String roleId) {
        return roleDao.findRoleByRoleId(roleId);
    }

    @Override
    public List<Permission> findOtherPermissionByRoleId(String roleId) {
        return roleDao.findOtherPermissionByRoleId(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] ids) {
        for (String permissionId:ids){
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }
}
