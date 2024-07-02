package com.pure.test.threadPool;

import com.pure.Application;
import com.pure.config.ThreadPoolConfig;
import com.pure.config.ThreadPoolConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ThreadPoolTest {
    @Autowired
    ThreadPoolConfig threadPoolConfig;
    @Autowired
    ThreadPoolConfigProperties threadPoolConfigProperties;

    @Test
    public void create() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        ThreadPoolExecutor threadPoolExecutor = threadPoolConfig.threadPoolExecutor(threadPoolConfigProperties);

        threadPoolExecutor.execute(() -> System.out.println("多线程测试"));
        System.out.println("主线程测试");
    }
}
