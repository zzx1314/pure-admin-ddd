package com.pure.infrastructure.persistent.mapper;

import io.mybatis.mapper.BaseMapper;
import io.mybatis.mapper.base.EntityProvider;
import io.mybatis.provider.Caching;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;

import java.io.Serializable;
import java.util.Optional;

public interface MyMapper<T, I extends Serializable> extends BaseMapper<T, I> {

    /**
     * 保存实体，默认主键自增，并且名称为 id
     * <p>
     * 这个方法是个示例，你可以在自己的接口中使用相同的方式覆盖父接口中的配置
     *
     * @param entity 实体类
     * @return 1成功，0失败
     */
    @Override
    @Lang(Caching.class)
    //@SelectKey(statement = "SELECT SEQ.NEXTVAL FROM DUAL", keyProperty = "id", before = true, resultType = long.class)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @InsertProvider(type = EntityProvider.class, method = "insert")
    int insert(T entity);

    /**
     * 保存实体中不为空的字段，默认主键自增，并且名称为 id
     * <p>
     * 这个方法是个示例，你可以在自己的接口中使用相同的方式覆盖父接口中的配置
     *
     * @param entity 实体类
     * @return 1成功，0失败
     */
    @Override
    @Lang(Caching.class)
    //@SelectKey(statement = "SELECT SEQ.NEXTVAL FROM DUAL", keyProperty = "id", before = true, resultType = long.class)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @InsertProvider(type = EntityProvider.class, method = "insertSelective")
    int insertSelective(T entity);

    /**
     * 查询
     * @param id 主键
     * @return
     */
    @Override
    @Lang(Caching.class)
    @SelectProvider(type = MyEntityProvider.class, method = "selectByPrimaryKey")
    Optional<T> selectByPrimaryKey(I id);
}
