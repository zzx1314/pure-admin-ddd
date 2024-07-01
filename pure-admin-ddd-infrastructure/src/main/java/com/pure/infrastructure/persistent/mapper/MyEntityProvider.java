package com.pure.infrastructure.persistent.mapper;

import io.mybatis.mapper.base.EntityProvider;
import io.mybatis.provider.EntityColumn;
import io.mybatis.provider.EntityTable;
import io.mybatis.provider.SqlScript;
import org.apache.ibatis.builder.annotation.ProviderContext;

import java.util.stream.Collectors;

public class MyEntityProvider extends EntityProvider {
    /**
     * 根据主键查询实体
     *
     * @param providerContext 上下文
     * @return cacheKey
     */
    public static String selectByPrimaryKey(ProviderContext providerContext) {
        return SqlScript.caching(providerContext, new SqlScript() {
            @Override
            public String getSql(EntityTable entity) {
                return "SELECT " + entity.baseColumnAsPropertyList()
                        + " FROM " + entity.tableName()
                        + where(() -> entity.idColumns().stream()
                        .map(EntityColumn::columnEqualsProperty).collect(Collectors.joining(" AND "))
                        + " AND is_deleted = 0"
                );
            }
        });
    }
}
