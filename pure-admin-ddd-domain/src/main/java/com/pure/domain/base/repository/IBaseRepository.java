package com.pure.domain.base.repository;

public interface IBaseRepository<T>{
    /**
     * 新增数据
     * @param t 实体类
     * @return 返回主键
     */
    int insert(T t);
}
