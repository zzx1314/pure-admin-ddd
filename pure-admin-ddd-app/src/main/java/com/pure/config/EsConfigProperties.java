package com.pure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "pure.elastic.config", ignoreInvalidFields = true)
public class EsConfigProperties {
    /**
     * ip地址
     */
    private String host;

    /**
     * 端口
     */
    private Integer port = 9200;

    /**
     * 模式
     */
    private String scheme;

    /**
     * 登录指纹
     */
    private String fingerprint;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
