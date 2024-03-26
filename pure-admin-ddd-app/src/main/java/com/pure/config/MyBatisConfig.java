package com.pure.config;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Slf4j
@EnableTransactionManagement
@MapperScan("com.pure.infrastructure.persistent.dao")
public class MyBatisConfig {
}
