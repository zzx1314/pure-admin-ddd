package com.pure.infrastructure.persistent.repository;

import com.pure.domain.user.repository.ISysUserRepository;
import com.pure.infrastructure.persistent.po.SysUserPO;
import org.springframework.stereotype.Repository;


/**
 * 用户仓储的实现
 */
@Repository
public class SysUserRepository extends BaseRepository<SysUserPO, Long> implements ISysUserRepository<SysUserPO, Long> {
}
