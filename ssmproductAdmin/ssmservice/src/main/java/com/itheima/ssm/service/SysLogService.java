package com.itheima.ssm.service;

import com.itheima.ssm.domain.SysLog;

import java.util.List;

public interface SysLogService {
    void save(SysLog sysLog);

    List<SysLog> findAll();
}
