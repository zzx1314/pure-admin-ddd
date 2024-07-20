package com.pure.domain.user.repository;


import com.pure.domain.base.repository.IBaseRepository;

import java.io.Serializable;

/**
 * 用户仓储服务接口
 */
public interface ISysUserRepository<T, I extends Serializable> extends IBaseRepository<T, I> {
    Object getSysUserByUsername(String username);
}
