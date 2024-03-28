package com.pure.test.db;

import com.pure.Application;
import com.pure.domain.user.repository.ISysUserRepository;
import com.pure.infrastructure.persistent.po.SysUserPO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MybatisTest {
    @Resource
    ISysUserRepository<SysUserPO, Integer> sysUserRepository;

    @Test
    public void testAdd() {
        SysUserPO sysUserPO = new SysUserPO();
        sysUserPO.setUsername("zhangsan");
        sysUserPO.setPassword("123456");
        sysUserPO.setCreateTime(LocalDateTime.now());
        sysUserRepository.insert(sysUserPO);
    }

    @Test
    public void testSelectById() {
        Optional<SysUserPO> sysUserPO = sysUserRepository.selectById(8);
        sysUserPO.ifPresent(one -> log.info("{}", one));
    }
}
