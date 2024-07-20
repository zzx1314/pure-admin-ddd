package com.pure.infrastructure.persistent.sysuser.repository;

import com.pure.domain.base.repository.IBaseRepository;
import com.pure.infrastructure.persistent.mapper.MyMapper;
import io.mybatis.activerecord.MapperRecord;

import java.io.Serializable;
import java.util.Optional;

public class BaseRepository <T,I extends Serializable> implements IBaseRepository<T, I>, MapperRecord<T, I, MyMapper<T, I>> {
    /**
     * 新增数据
     * @param t 实体类
     * @return
     */
    @Override
    public int insert(T t) {
        return this.baseMapper().insert(t);
    }

    /**
     * 更新数据
     * @param t 实体类
     * @return
     */
    @Override
    public int updateById(T t) {
        return this.baseMapper().updateByPrimaryKey(t);
    }

    /**
     * 通过实体类进行删除
     * @param t 实体类
     * @return
     */
    @Override
    public int delete(T t) {
        return this.baseMapper().delete(t);
    }

    /**
     * 通过实体类进行删除
     * @param id id
     * @return
     */
    @Override
    public int deleteById(I id) {
        return this.baseMapper().deleteByPrimaryKey(id);
    }

    /**
     * 通过id进行查询
     * @param id id
     * @return
     */
    @Override
    public Optional<T> selectById(I id) {
        return this.baseMapper().selectByPrimaryKey(id);
    }

    /**
     * 通过实体类进行查询
     * @param t 实体类
     * @return
     */
    @Override
    public Optional<T> selectOneByEntity(T t) {
        return this.baseMapper().selectOne(t);
    }
}
