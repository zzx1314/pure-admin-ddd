package com.pure.infrastructure.persistent.sysuser.repository;

import com.pure.domain.user.repository.ISysUserRepository;
import com.pure.infrastructure.persistent.sysuser.dao.SysUserMapper;
import com.pure.infrastructure.persistent.sysuser.po.SysUserPO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


/**
 * 用户仓储的实现
 */
@Repository
public class SysUserRepository extends BaseRepository<SysUserPO, Long> implements ISysUserRepository<SysUserPO, Long> {
    @Resource
    private SysUserMapper sysUserMapper;


    @Override
    public Object getSysUserByUsername(String username) {
        return sysUserMapper.getSysUserByUsername(username);
    }
}
