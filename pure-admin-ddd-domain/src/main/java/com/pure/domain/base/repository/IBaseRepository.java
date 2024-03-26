package com.pure.domain.base.repository;

import java.io.Serializable;
import java.util.Optional;

public interface IBaseRepository<T, I extends Serializable>{
    /**
     * 新增数据
     * @param t 实体类
     * @return 返回主键
     */
    int insert(T t);

    /**
     * 通过实体类的id进行更新
     * @param t 实体类
     * @return 返回主键
     */
    int updateById(T t);

    /**
     * 通过实体类进行删除
     * @param t 实体类
     * @return
     */
    int delete(T t);

    /**
     * 通过id进行删除
     * @param id id
     * @return
     */
    int deleteById(I id);

    /**
     * 通过id进行查询
     * @param id id
     * @return
     */
    Optional<T> selectById(I id);

    /**
     * 通过实体类进行查询
     * @param t 实体类
     * @return
     */
    Optional<T> selectOneByEntity(T t);
}
