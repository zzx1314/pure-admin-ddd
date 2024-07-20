package com.pure.infrastructure.persistent.sysuser.dao;

import com.pure.infrastructure.persistent.mapper.MyMapper;
import com.pure.infrastructure.persistent.sysuser.po.SysUserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserMapper extends MyMapper<SysUserPO, Long> {

    SysUserPO getSysUserByUsername(@Param("username") String username);
}
