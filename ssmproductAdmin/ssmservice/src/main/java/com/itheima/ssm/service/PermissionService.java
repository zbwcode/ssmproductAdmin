package com.itheima.ssm.service;


import com.itheima.ssm.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionService {
    List<Permission> findAll();

    void savePermission(Permission permission);
}
